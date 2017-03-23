
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;

	public SecondRatings() {
		// default constructor
		this("data/ratedmoviesfull.csv", "data/ratings.csv");
	}

	public SecondRatings(String moviefile, String ratingsfile) {
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(moviefile);
		myRaters = fr.loadRaters(ratingsfile);
	}

	public int getMovieSize() {
		return myMovies.size();
	}

	public int getRaterSize() {
		return myRaters.size();
	}

	private double getAverageById(String id, int minimalRaters) {
		int sum = 0;
		double average = 0.0d;
		ArrayList<Rater> ratings = new ArrayList<Rater>();
		for(Rater rater : myRaters) {
			if(rater.hasRating(id)) {
				ratings.add(rater);
			}
		}
		for(Rater rater : ratings) {
			if(ratings.size() >= minimalRaters) {
				sum += rater.getRating(id);
				average = sum / (double) ratings.size();
			}
		}
		return average;
	}

	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for(Movie movie : myMovies) {
			double avgRating = getAverageById(movie.getID(), minimalRaters);
			if(avgRating != 0) {
				Rating rating = new Rating(movie.getID(), avgRating);
				ratings.add(rating);
			}
		}
		return ratings;
	}

	public String getTitle(String id) {
		for(Movie movie : myMovies) {
			if(movie.getID().equals(id)) {//.contains works as well
				return movie.getTitle();
			}
		}
		return "The movie ID " + id + " is not found.";
	}

	public String getID(String title) {
		for(Movie movie : myMovies) {
			if(movie.getTitle().equals(title)) {
				return movie.getID();
			}
		}
		return "NO SUCH TITLE.";
	}
}
