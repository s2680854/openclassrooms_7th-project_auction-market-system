package com.nnk.springboot.controller.rating;

import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingDeletionService {

    @Autowired
    private RatingRepository ratingRepository;

    public void deleteRatingById(Long id) {

        ratingRepository.deleteById(id);
    }

    public void deleteRatings() {

        ratingRepository.deleteAll();
    }
}
