import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	
	public static void main(String[] args) {
		MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
		mrwf.printAverageRatings();
		mrwf.printAverageRatingsByYearAfter();
		mrwf.printAverageRatingsByGenre();
		mrwf.printAverageRatingsByMinutes();
		mrwf.printAverageRatingsByDirectors();
		mrwf.printAverageRatingsByYearAfterAndGenre();
		mrwf.printAverageRatingsByDirectorsAndMinutes();
	}
	
	public void printAverageRatings() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		System.out.println("---Average Ratings---");
		ArrayList<Rating> ratings = sr.getAverageRatings(35);
		Collections.sort(ratings);
		System.out.println("Number of movies: " + ratings.size());
//		for(Rating rating : ratings) {
//			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByYearAfter() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		
		YearAfterFilter yf = new YearAfterFilter(2000);
		System.out.println("---Average Ratings By Year After---");
		ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(20, yf);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
//		for(Rating rating : ratings) {
//			System.out.println(MovieDatabase.getYear(rating.getItem()) + "\t" + rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByGenre() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		
		GenreFilter gf = new GenreFilter("Comedy");
		System.out.println("---Average Ratings By Genre---");
		ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(20, gf);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
//		for(Rating rating : ratings) {
//			System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
//			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByMinutes() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		
		MinutesFilter mf = new MinutesFilter(105, 135);
		System.out.println("---Average Ratings By Minutes---");
		ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(5, mf);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
//		for(Rating rating : ratings) {
//			System.out.println("Time: " + MovieDatabase.getMinutes(rating.getItem()) + " \t" + rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByDirectors() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		
		DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
		System.out.println("---Average Ratings By Directors---");
		ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(4, df);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
//		for(Rating rating : ratings) {
//			System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
//			System.out.println("\t"+MovieDatabase.getDirector(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		
		YearAfterFilter yf = new YearAfterFilter(1990);
		GenreFilter gf = new GenreFilter("Drama");
		AllFilters af = new AllFilters();
		af.addFilter(yf);
		af.addFilter(gf);
		System.out.println("---Average Ratings By Year After & Genre---");
		ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(8, af);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
//		for(Rating rating : ratings) {
//			System.out.println("Year: " + MovieDatabase.getYear(rating.getItem()) + "\t" + rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
//			System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
//		}
	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings sr = new ThirdRatings("data/ratings.csv"); 
		System.out.println("Number of raters: " + sr.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv"); //initialize movies
		System.out.println("Number of movies: " + MovieDatabase.size());
		
		MinutesFilter mf = new MinutesFilter(90, 180);
		DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
		AllFilters af = new AllFilters();
		af.addFilter(mf);
		af.addFilter(df);
		System.out.println("---Average Ratings By Directors & Minutes---");
		ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(3, af);
		Collections.sort(ratings);
		System.out.println("found " + ratings.size() + " movies");
//		for(Rating rating : ratings) {
//			System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
//			System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
//		}
	}
}
