package com.nnk.springboot.service.bidslist.rating;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingUpdateService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating updateRating(Rating rating) {

        Optional<Rating> optionalRating =
                ratingRepository.findById(rating.getId());

        if (!optionalRating.isPresent()) {

            return new Rating(rating.getMoodysRating(), rating.getSandPRating(),
                    rating.getFitchRating(), rating.getOrderNumber());
        }
        return ratingRepository.save(rating);
    }
}
