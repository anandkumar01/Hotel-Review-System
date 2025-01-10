package com.learning.rating.services;

import com.learning.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    // create ratings
    Rating create(Rating rating);

    // get all ratings
    List<Rating> getRatings();

    // get all ratings bu user id
    List<Rating> getRatingByUserId(String userId);

    // get all ratings by hotel id
    List<Rating> getRatingByHotelId(String hotelId);

}
