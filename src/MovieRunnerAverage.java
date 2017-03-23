import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

	public static void main(String[] args) {
		MovieRunnerAverage mra = new MovieRunnerAverage();
		mra.printAverageRatings();
//		mra.getAverageRatingOneMovie();
	}
	
	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
		
		System.out.println("Number of movies: " + sr.getMovieSize());
		System.out.println("Number of raters: " + sr.getRaterSize());
//		System.out.println("Title of the movie: " + sr.getTitle("1798709")); //Her
//		System.out.println("Title of the movie: " + sr.getTitle("0068646")); //The Godfather
//		System.out.println("Title of the movie: " + sr.getTitle("0113277")); //Heat
//		System.out.println("Title of the movie: " + sr.getTitle("0790636")); //Dallas Buyers Club
		
		ArrayList<Rating> ratings = sr.getAverageRatings(12);
		Collections.sort(ratings);
		for(Rating rating : ratings) {
			System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
		}
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
		String title = "Vacation";
//		String title = "No Country for Old Men";
//		String title = "Inside Llewyn Davis";
		String movieID = sr.getID(title);
		ArrayList<Rating> ratings = sr.getAverageRatings(0);
		Collections.sort(ratings);
		for(Rating rating : ratings) {
			if(rating.getItem().equals(movieID)) {
				System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
			}
		}
	}
}
