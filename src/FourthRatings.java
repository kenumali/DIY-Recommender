
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {

	private double getAverageById(String id, int minimalRaters) {
		ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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

	private double dotProduct(Rater me, Rater r) {
		double dotProduct = 0.0;
		ArrayList<String> myMovies = me.getItemsRated();
		for(String id : myMovies) {
			if(r.hasRating(id)) {
				double myRating = me.getRating(id);
				double rRating = r.getRating(id);
				myRating -= 5;
				rRating -= 5;
				dotProduct += myRating * rRating;
				System.out.println("movie: " + id + " dotProduct: " + dotProduct);
			}
		}
		return dotProduct;
	}

	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		Rater me = RaterDatabase.getRater(id);
		for(Rater r : RaterDatabase.getRaters()) {
			if(!r.equals(me)) {
				double product = dotProduct(me, r);
				if(product > 0) {
					list.add(new Rating(r.getID(), product));
				}
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}

	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(String movieID : movies) {
			double weightedAverage = 0.0;
			double sum = 0.0;
			int ratersCount = 0;
			for(int i = 0; i < numSimilarRaters; i++) {
				Rating rating = similarRatings.get(i);
				String raterID  = rating.getItem();
				double value = rating.getValue();
				Rater myRater = RaterDatabase.getRater(raterID);
				if(myRater.hasRating(movieID)) {
					ratersCount++;
					sum += value * myRater.getRating(movieID);
				}
			}
			if(ratersCount >= minimalRaters) {
				weightedAverage = sum / (double) ratersCount;
				list.add(new Rating(movieID, weightedAverage));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
//		return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for(String movieID : movies) {
			double weightedAverage = 0.0;
			double sum = 0.0;
			int ratersCount = 0;
			for(int i = 0; i < numSimilarRaters; i++) {
				Rating rating = similarRatings.get(i);
				String raterID  = rating.getItem();
				double value = rating.getValue();
				Rater myRater = RaterDatabase.getRater(raterID);
				if(myRater.hasRating(movieID)) {
					ratersCount++;
					sum += value * myRater.getRating(movieID);
				}
			}
			if(ratersCount >= minimalRaters) {
				weightedAverage = sum / (double) ratersCount;
				list.add(new Rating(movieID, weightedAverage));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}
}
