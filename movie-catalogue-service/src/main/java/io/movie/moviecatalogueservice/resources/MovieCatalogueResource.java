package io.movie.moviecatalogueservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.movie.moviecatalogueservice.models.CatalogueItem;
import io.movie.moviecatalogueservice.models.Movie;
import io.movie.moviecatalogueservice.models.UserRating;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userId}")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId) {
		// Movie movie = restTemplate.getForObject("http://localhost:8081/movies/foo", Movie.class);
		// List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("4567", 5));
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId,
				UserRating.class);
		return ratings.getUserRating().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogueItem(movie.getMovieName(), "dummy movie", rating.getRating());
		}).collect(Collectors.toList());
	}
}
// This is initial first hard code standalone rest implementation
// @RequestMapping("/{userId}")
// public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId) {
// return Collections.singletonList(new CatalogueItem("movieTest", "dummy movie", 0));
// }