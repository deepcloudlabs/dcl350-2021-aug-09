package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@RestController // Spring MVC -> Spring Web
@RequestScope
@RequestMapping("/movies")
@CrossOrigin
public class ImdbRestController {
	private MovieService movieService;
	
	public ImdbRestController(MovieService movieService) {
		this.movieService = movieService;
	}

	// http://localhost:9001/movies?from=1970&to=1979 
	@GetMapping(params = {"from","to"}) // query parameters: from, to
	public Collection<Movie> findMoviesByYearTange(@RequestParam int from,@RequestParam  int to){
		return movieService.findAllMoviesByYearRange(from, to);
	}
	
}
