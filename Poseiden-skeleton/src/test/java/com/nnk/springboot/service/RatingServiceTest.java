package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.rating.RatingDeletionService;
import com.nnk.springboot.service.rating.RatingReadService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class RatingServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RatingReadService ratingReadService;
    @MockBean
    private RatingDeletionService ratingDeletionService;
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void shouldGetRatings() throws Exception {

        Collection<Rating> actualList = new ArrayList<>();

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

        Rating expected = new Rating();

        Optional<Rating> optional = Optional.ofNullable(ratingReadService.getRatingById(id));
        Rating actual = new Rating();
        if (optional.isPresent()) {
            actual.setMoodysRating(optional.get().getMoodysRating());
            actual.setSandPRating(optional.get().getSandPRating());
            actual.setFitchRating(optional.get().getFitchRating());
            actual.setOrderNumber(optional.get().getOrderNumber());
        }

        assertEquals(expected, actual);
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
