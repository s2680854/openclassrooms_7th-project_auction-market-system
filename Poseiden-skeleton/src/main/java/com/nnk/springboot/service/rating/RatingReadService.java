package com.nnk.springboot.service.rating;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RatingReadService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating getRatingById(Long id) {

        return ratingRepository.getById(id);
    }

    public Collection<Rating> getRatings() {

        return ratingRepository.findAll();
    }
}
