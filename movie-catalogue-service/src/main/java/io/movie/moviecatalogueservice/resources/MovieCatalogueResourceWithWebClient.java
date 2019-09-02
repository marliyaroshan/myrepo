package io.movie.moviecatalogueservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.movie.moviecatalogueservice.models.CatalogueItem;
import io.movie.moviecatalogueservice.models.Movie;
import io.movie.moviecatalogueservice.models.Rating;

@RestController
@RequestMapping("/catalogueweb")
public class MovieCatalogueResourceWithWebClient {
	@Autowired
	private WebClient.Builder webClientBuilder;

	@GetMapping("/{userId}")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId") String userId) {
		// WebClient.Builder webBuilder = WebClient.builder();
		List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("4567", 5));
		return ratings.stream().map(rating -> {
			// Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),
			// Movie.class);
			Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
					.retrieve().bodyToMono(Movie.class).block();
			return new CatalogueItem(movie.getMovieName(), "dummy movie", rating.getRating());
		}).collect(Collectors.toList());
	}
}
