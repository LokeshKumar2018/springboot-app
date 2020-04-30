
package com.lokesh.moviecatalogservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.netflix.discovery.DiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder web111;

        @RequestMapping(method = RequestMethod.GET, value = "/{userId}",produces = "application/JSON")
        public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId) {
            List<CatalogItem> catalogs= new ArrayList<>();


    // List<Rating> ratings = this.exchangeAsList("http://localhost:8083/ratingdata/users/1", new ParameterizedTypeReference<List<Rating>>(){});          
    
    //http://localhost:8083, we updated with application name registed in eureka server
    UserRating ratings = restTemplate.getForObject("http://rating-app/ratingdata/users/1", UserRating.class);  
        
    for (Rating rating : ratings.getRatings()) {
        Movie movie = web111.build().get().uri("http://movieinfo-app/movies/"+rating.getMovieId()).retrieve().
       bodyToMono(Movie.class).block();
       
     // Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
        catalogs.add(new CatalogItem(movie.getName(),"Movie is 00ff0f0k",rating.getRating()));
    }

    return catalogs;
    }

    public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }
}


//RestTemplate call is syncronous. To make it asynchronous, use reactive prgmin, like WebClient
//  method should also retun asynchouns object like Mono/Flux be aynchronous,
// public List<Mono/Flux> getCatalogItem(...){}