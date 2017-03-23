import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

	public static void main(String[] args) {
		MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
//		mrsr.printAverageRatings();
//		mrsr.printAverageRatingsByYearAfterAndGenre();
		mrsr.printSimilarRatings();
//		mrsr.printSimilarRatingsByGenre();
//		mrsr.printSimilarRatingsByDirector();
//		mrsr.printSimilarRatingsByGenreAndMinutes();
//		mrsr.printSimilarRatingsByYearAfterAndMinutes();
	}
	
	public void printAverageRatings() {
		FourthRatings fr = new FourthRatings(); 

		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies

		System.out.println("---Average Ratings---");
		ArrayList<Rating> ratings = fr.getAverageRatings(35);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
		//		for(Rating rating : ratings) {
		//			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
		//		}
	}

	public void printAverageRatingsByYearAfterAndGenre() {
		FourthRatings fr = new FourthRatings(); 

		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies

		YearAfterFilter yf = new YearAfterFilter(1990);
		GenreFilter gf = new GenreFilter("Drama");
		AllFilters af = new AllFilters();
		af.addFilter(yf);
		af.addFilter(gf);
		System.out.println("---Average Ratings By Year After & Genre---");
		ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(8, af);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
		//		for(Rating rating : ratings) {
		//			System.out.println("Year: " + MovieDatabase.getYear(rating.getItem()) + "\t" + rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
		//			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
		//		}
	}
	
	public void printSimilarRatings() {
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		
		String raterID = "71";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		
		ArrayList<Rating> ratings = fr.getSimilarRatings(raterID, numSimilarRaters,minimalRaters);
		for(Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\tRating: " + rating.getValue());
		}
	}
	
	public void printSimilarRatingsByGenre() {
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		
		String raterID = "964";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		GenreFilter gf = new GenreFilter("Mystery");
		
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, gf);
		for(Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\tRating: " + rating.getValue());
			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
		}
	}
	
	public void printSimilarRatingsByDirector() {
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		
		String raterID = "120";
		int numSimilarRaters = 10;
		int minimalRaters = 2;
		DirectorsFilter df = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, df);
		for(Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\tRating: " + rating.getValue());
			System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
		}
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		
		String raterID = "168";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		MinutesFilter mf = new MinutesFilter(80, 160);
		GenreFilter gf = new GenreFilter("Drama");
		AllFilters af = new AllFilters();
		af.addFilter(mf);
		af.addFilter(gf);
		
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, af);
		for(Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + MovieDatabase.getMinutes(rating.getItem()) + "\tRating: " + rating.getValue());
			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
		}
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes() {
		FourthRatings fr = new FourthRatings();
		RaterDatabase.initialize("ratings.csv"); //initialize raters
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		
		String raterID = "314";
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		MinutesFilter mf = new MinutesFilter(70, 200);
		YearAfterFilter yf = new YearAfterFilter(1975);
		AllFilters af = new AllFilters();
		af.addFilter(mf);
		af.addFilter(yf);
		
		ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, af);
		for(Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + MovieDatabase.getYear(rating.getItem()) + "\t" + 
								MovieDatabase.getMinutes(rating.getItem()) + "\tRating: " + rating.getValue());
		}
	}
}
