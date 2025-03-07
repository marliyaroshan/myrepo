package io.movie.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.movie.ratingdataservice.models.Rating;
import io.movie.ratingdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingInfoResource {
	@RequestMapping("/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@GetMapping("/users/{userId}")
	public UserRating getMovieRatingForUser(@PathVariable("userId") String userId) {
		List<Rating> ratingList = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratingList);
		return userRating;
	}
}
