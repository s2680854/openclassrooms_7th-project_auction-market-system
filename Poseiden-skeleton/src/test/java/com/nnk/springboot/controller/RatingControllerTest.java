package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingController ratingController;

    @Before("")
    public void setup() {

        ratingController = new RatingController();
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    public void shouldGetRatingList() throws Exception {

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"));
    }

    @Test
    public void shouldAddRating() throws Exception {

        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    public void shouldShowUpdateRatingForm() throws Exception {

        ratingController.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(0);
        ratingRepository.save(rating);
        Long id = ratingRepository.findByOrderNumber(0).getId();

        mockMvc.perform(get("/rating/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    @Test
    public void shouldValidateRating() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(4);
        ratingRepository.save(rating);

        rating.setMoodysRating("10");
        Long id = ratingRepository.findByOrderNumber(4).getId();
        rating.setId(id);

        mockMvc.perform(post("/rating/validate")
                        .param("id", rating.getId().toString())
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", rating.getMoodysRating())
                        .param("orderNumber", String.valueOf(rating.getOrderNumber())))
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void shouldUpdateRating() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(5);
        ratingRepository.save(rating);

        rating.setMoodysRating("5");
        Long id = ratingRepository.findByOrderNumber(5).getId();
        rating.setId(id);

        mockMvc.perform(post("/rating/update/" + id)
                        .param("id", rating.getId().toString())
                        .param("moodysRating", rating.getMoodysRating())
                        .param("sandPRating", rating.getSandPRating())
                        .param("fitchRating", rating.getMoodysRating())
                        .param("orderNumber", String.valueOf(rating.getOrderNumber())))
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void shouldDeleteRating() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(6);
        ratingRepository.save(rating);
        Long id = ratingRepository.findByOrderNumber(6).getId();

        mockMvc.perform(delete("/rating/delete/" + id))
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void shouldDeleteRatingList() throws Exception {

        ratingController.deleteAll();
        Rating rating = new Rating();
        rating.setMoodysRating("0");
        rating.setSandPRating("0");
        rating.setFitchRating("0");
        rating.setOrderNumber(0);
        ratingRepository.save(rating);
        Long id = ratingRepository.findByOrderNumber(0).getId();

        mockMvc.perform(delete("/rating/delete/" + id))
                .andExpect(view().name("redirect:/rating/list"));
    }
}
