
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
	private ArrayList<Rater> myRaters;

	public ThirdRatings() {
		// default constructor
		this("data/ratings.csv");
	}

	public ThirdRatings(String ratingsfile) {
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(ratingsfile);
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
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(String movieID : movies) {
			double avgRating = getAverageById(movieID, minimalRaters);
			if(avgRating != 0) {
				Rating rating = new Rating(movieID, avgRating);
				ratings.add(rating);
			}
		}
		return ratings;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for(String movieID : movies) {
			double avgRating = getAverageById(movieID, minimalRaters);
			if(avgRating != 0) {
				Rating rating = new Rating(movieID, avgRating);
				ratings.add(rating);
			}
		}
		return ratings;
	}
}
