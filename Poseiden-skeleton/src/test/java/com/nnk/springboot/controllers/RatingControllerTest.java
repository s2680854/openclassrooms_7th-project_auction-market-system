package com.nnk.springboot.controllers;

import com.nnk.springboot.controller.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
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
    public void shouldShowUpdateTradeForm() throws Exception {

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
    public void shouldDeleteTrade() throws Exception {

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
