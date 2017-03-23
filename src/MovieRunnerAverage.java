import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

	public static void main(String[] args) {
		MovieRunnerAverage mra = new MovieRunnerAverage();
		mra.printAverageRatings();
		mra.getAverageRatingOneMovie();
	}

	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");

		System.out.println("Number of movies: " + sr.getMovieSize());
		System.out.println("Number of raters: " + sr.getRaterSize());

		ArrayList<Rating> ratings = sr.getAverageRatings(12);
		Collections.sort(ratings);
		for(Rating rating : ratings) {
			System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
		}
	}

	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
		String title = "Vacation";
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
