package com.lokesh.ratingdataservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

    
    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}",produces = "application/JSON")
    public Rating getMovies(@PathVariable("movieId") String movieId) {

    return new Rating(movieId,5);
}

@RequestMapping(method = RequestMethod.GET, value = "/users/{userId}",produces = "application/JSON")
    public UserRating getRatings(@PathVariable("userId") String userId) {

      UserRating ur= new UserRating();
     
    List<Rating> r= Arrays.asList(new Rating("Lokesh1",40),new Rating("Lokesh2",50));
    ur.setRatings(r);

    return ur;
  
  }
}