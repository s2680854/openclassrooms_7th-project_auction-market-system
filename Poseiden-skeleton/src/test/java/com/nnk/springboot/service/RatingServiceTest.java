package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.rating.RatingCreationgService;
import com.nnk.springboot.service.rating.RatingDeletionService;
import com.nnk.springboot.service.rating.RatingReadService;
import com.nnk.springboot.service.rating.RatingUpdateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters=false)
public class RatingServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RatingCreationgService ratingCreationgService;
    @Autowired
    private RatingReadService ratingReadService;
    @Autowired
    private RatingUpdateService ratingUpdateService;
    @MockBean
    private RatingDeletionService ratingDeletionService;
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void shouldCreateRating() throws Exception {

        ratingRepository.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(1);
        ratingCreationgService.createRating(rating);
        rating.setId(ratingRepository.findByOrderNumber(rating.getOrderNumber()).getId());

        Rating actual = ratingReadService.getRatingById(rating.getId());

        assertEquals(rating, actual);
    }

    @Test
    public void shouldGetRatings() throws Exception {

        ratingRepository.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(1);
        ratingCreationgService.createRating(rating);
        rating.setId(ratingRepository.findByOrderNumber(rating.getOrderNumber()).getId());
        Collection<Rating> actualList = new ArrayList<>();
        actualList.add(rating);

        Collection<Rating> expectedList = ratingReadService.getRatings();

        assertEquals(actualList, expectedList);
    }

    @Test
    public void shouldGetRatingById() throws Exception {

        ratingRepository.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(1);
        ratingRepository.save(rating);
        Long id = 1L;
        try {id = ratingRepository.findByOrderNumber(1).getId();} catch (Exception e) {}
        rating.setId(id);

        Optional<Rating> optional = Optional.ofNullable(ratingReadService.getRatingById(id));
        Rating actual = new Rating();
        if (optional.isPresent()) {
            actual.setMoodysRating(optional.get().getMoodysRating());
            actual.setSandPRating(optional.get().getSandPRating());
            actual.setFitchRating(optional.get().getFitchRating());
            actual.setOrderNumber(optional.get().getOrderNumber());
        }

        assertEquals(rating, actual);
    }

    @Test
    public void shouldUpdateRating() throws Exception {

        ratingRepository.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(1);
        ratingCreationgService.createRating(rating);
        rating.setId(ratingRepository.findByOrderNumber(rating.getOrderNumber()).getId());
        rating.setMoodysRating("45");
        ratingUpdateService.updateRating(rating);

        Rating actual = ratingReadService.getRatingById(rating.getId());

        assertEquals(rating, actual);
    }


    @Test
    public void shouldDeleteRating() throws Exception {

        ratingRepository.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(2);
        ratingRepository.save(rating);
        Long ratingId = ratingRepository.findByOrderNumber(2).getId();

        Mockito.doNothing().when(ratingDeletionService).deleteRatingById(ratingId);

        mockMvc.perform(delete("/rating/delete/" + ratingId));

        Mockito.verify(ratingDeletionService, Mockito.times(1)).deleteRatingById(ratingId);
    }
}
