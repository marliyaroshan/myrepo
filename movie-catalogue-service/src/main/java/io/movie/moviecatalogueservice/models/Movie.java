package io.movie.moviecatalogueservice.models;

public class Movie {
	public Movie() {
	}

	public Movie(String movieId, String movieName) {
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	private String movieId;
	private String movieName;
}
