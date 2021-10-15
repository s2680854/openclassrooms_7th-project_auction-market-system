package com.nnk.springboot.controllers.rating;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RatingCreationgService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating createRating(Rating rating) {

        return ratingRepository.save(rating);
    }

    public Collection<Rating> createRatings(Collection<Rating> ratings) {

        return ratingRepository.saveAll(ratings);
    }
}
