package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating getRating(Long id) {

        return ratingRepository.getById(id);
    }

    public Collection<Rating> getRatings() {

        return ratingRepository.findAll();
    }

    public Rating createRating(Rating rating) {

        return ratingRepository.save(rating);
    }

    public Collection<Rating> createRatings(Collection<Rating> ratings) {

        return ratingRepository.saveAll(ratings);
    }

    public Rating updateRating(Rating rating) {

        Optional<Rating> optionalRating =
                ratingRepository.findById(rating.getId());

        if (!optionalRating.isPresent()) {

            return new Rating(rating.getMoodysRating(), rating.getSandPRating(),
                    rating.getFitchRating(), rating.getOrderNumber());
        }
        return ratingRepository.save(rating);
    }

    public void deleteRatingById(Long id) {

        ratingRepository.deleteById(id);
    }

    public void deleteRatings() {

        ratingRepository.deleteAll();
    }
}
