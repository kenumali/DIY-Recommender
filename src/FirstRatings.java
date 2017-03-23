import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord record : parser) {
			String movieID = record.get("id");
			String title = record.get("title");
			String year = record.get("year");
			String country = record.get("country");
			String genre = record.get("genre");
			String director = record.get("director");
			int minutes = Integer.parseInt(record.get("minutes"));
			String poster = record.get("poster");

			movies.add(new Movie(movieID, title, year, genre, director, country, poster, minutes));
		}
		return movies;
	}

	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> raters = new ArrayList<Rater>();
		HashMap<String, ArrayList<Rating>> myMap = new HashMap<String, ArrayList<Rating>>();
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord record : parser) {
			String raterID = record.get("rater_id");
			String movieID = record.get("movie_id");
			Double rating = Double.parseDouble(record.get("rating"));
			String time = record.get("time");

			Rating ratings;
			ArrayList<Rating> ratingsList;
			if(!myMap.containsKey(raterID)) {
				ratings = new Rating(movieID, rating);
				ratingsList = new ArrayList<Rating>();
				ratingsList.add(ratings);
				myMap.put(raterID, ratingsList);
			} else {
				ratingsList = myMap.get(raterID);
				ratings = new Rating(movieID, rating);
				ratingsList.add(ratings);
				myMap.put(raterID, ratingsList);
			}
		}
		for(String id : myMap.keySet()) {
			Rater rater = new Rater(id);
			ArrayList<Rating> ratings = myMap.get(id);
			for(Rating rating : ratings) {
				rater.addRating(rating.getItem(), rating.getValue());
			}
			raters.add(rater);
		}
		return raters;
	}

	public void testLoadMovies() {
		ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");
		int counter = 0;

		for(Movie movie : movies) {
			//			System.out.println(movie);

			//			code to determine how many movies are in the Comedy genre
			//			if(movie.getGenres().contains("Comedy")) {
			//				counter++;
			//			}

			//			code to determine how many movies are greater than 150 mins
			//			if(movie.getMinutes() > 150) {
			//				counter++;
			//			}

			//			code to determine the maximum number of movies by any director, and who
			//			the directors are that directed that many movies.  
			String[] directors = movie.getDirector().split(", "); //directors > 1 is separated by ', '
			if(directors.length > 0) {
				System.out.println(movie.getDirector());
				counter++;
			}
		}
		//		System.out.println("Number of movies in Comedy genre: " + counter);
		//		System.out.println("Number of movies greater than 150 mins: " + counter);
		System.out.println("Number of directors: " + counter);
		System.out.println("Number of movies: " + movies.size());
	}

	public void testLoadRaters() {
		ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
		for(Rater rater : raters) {
			ArrayList<String> itemsRated = rater.getItemsRated();
			System.out.println("rater " + rater.getID() + " has " + rater.numRatings() + " ratings");
			for(int i = 0; i < itemsRated.size(); i++) {
				System.out.println(itemsRated.get(i) + "\t" + rater.getRating(itemsRated.get(i)));
			}
		}
		System.out.println("Number of raters: " + raters.size());
	}
}
