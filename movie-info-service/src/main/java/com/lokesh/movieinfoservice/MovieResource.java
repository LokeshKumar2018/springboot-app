package com.lokesh.movieinfoservice;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {



    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}",produces = "application/JSON")
    public Movie getMovies(@PathVariable("movieId") String movieId) {

    return new Movie(movieId,"This movie about transformer");
    
}
}