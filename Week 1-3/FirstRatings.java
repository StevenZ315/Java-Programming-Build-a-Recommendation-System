
/**
 * Write a description of FirstRatings here.
 * 
 * @author StevenZ
 * @version 05/15/20
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
        FileResource fr = new FileResource(filename);
        for (CSVRecord record : fr.getCSVParser()) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            
            movies.add(new Movie(id, title, year, genre, director, country, poster, minutes));
        }
        return movies;
    }
    
    public ArrayList<Movie> filter(ArrayList<Movie> someMovies, Filter mf) {
        ArrayList<Movie> ret = new ArrayList<Movie>();
        
        for (Movie m : someMovies) {
            if (mf.satisfies(m.getID())) {
                ret.add(m);
            }
        }
        return ret;
    }

    public ArrayList<String> getDirectorWithMostMovies(ArrayList<Movie> someMovies) {
        ArrayList<String> directors = new ArrayList<String>();
        HashMap<String, Integer> dirCounts = new HashMap<String, Integer>();
        
        for (Movie m : someMovies) {
            String dir = m.getDirector();
            if (dirCounts.containsKey(dir)) {
                dirCounts.put(dir, dirCounts.get(dir) + 1);
            } else {
                dirCounts.put(dir, 1);
            }
        }
        
        int maxMovies = Collections.max(dirCounts.values());
        System.out.println("Max movies per director: " + maxMovies);
        for (String dir : dirCounts.keySet()) {
            if (dirCounts.get(dir) == maxMovies) {
                directors.add(dir);
            }
        }
        return directors;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        ArrayList<String> raterIDs = new ArrayList<String>();
        FileResource fr = new FileResource(filename);
        
        for (CSVRecord record : fr.getCSVParser()) {
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            String time = record.get("time");
            
            if (raterIDs.contains(raterId)) {
                Iterator<Rater> raterIterator = raters.iterator();
                while (raterIterator.hasNext()) {
                    Rater r = raterIterator.next();
                    if (r.getID().equals(raterId)) {
                        r.addRating(movieId, rating);
                        break;
                    }
                }
            } else {
                Rater newRater = new EfficientRater(raterId);
                newRater.addRating(movieId, rating);
                raters.add(newRater);
                raterIDs.add(raterId);
            }
        }
        return raters;
    }
    
    public ArrayList<String> getRaterWithMostRatings(ArrayList<Rater> raters) {
        ArrayList<String> raterIDs = new ArrayList<String>();
        HashMap<Integer, ArrayList<Rater>> numRaterHash = new HashMap<Integer, ArrayList<Rater>>();
        
        for (Rater r : raters) {
            int num = r.numRatings();
            
            ArrayList<Rater> currRaters = numRaterHash.getOrDefault(num, new ArrayList<Rater>());
            currRaters.add(r);
            numRaterHash.put(num, currRaters);
        }
        
        int maxRates = Collections.max(numRaterHash.keySet());
        System.out.println("Max ratings per rater: " + maxRates);
        for (Rater r : numRaterHash.get(maxRates)) {
            raterIDs.add(r.getID());
        }
        return raterIDs;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        //System.out.println("Movies read: " + movies.size());
        
        
        GenreFilter gf = new GenreFilter("Comedy");
        ArrayList<Movie> cFiltered = filter(movies, gf);
        System.out.println(gf.getName());
        System.out.println("Movies count: " + cFiltered.size());
        
       
        MinutesFilter mmf = new MinutesFilter(150, 999);
        ArrayList<Movie> mmFiltered = filter(movies, mmf);
        System.out.println(mmf.getName());
        System.out.println("Movies count: " + mmFiltered.size());
        
        ArrayList<String> directors = getDirectorWithMostMovies(movies);
        System.out.println("Directors with most movies:\n" + directors);
        
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Total number of raters: " + raters.size());

        for (Rater r : raters) {
            if (r.getID().equals(new String("193"))) {
                System.out.println("Number of ratings " + r.numRatings());
                break;
            }
        }
        
        ArrayList<String> raterWithMostRatings = getRaterWithMostRatings(raters);
        System.out.println("Raters with most ratings:\n" + raterWithMostRatings);
        
        String movieID = "1798709";
        int raterOfMovie = 0;
        for (Rater r : raters) {
            if (r.getItemsRated().contains(movieID)) {
                 raterOfMovie++;
            }
        }
        System.out.println("Num of ratings for " + movieID + " is " + raterOfMovie);

        int numMovieRated = 0;
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (Rater r : raters) {
            for (String item : r.getItemsRated()) {
                if (! moviesRated.contains(item)) {
                    moviesRated.add(item);
                    numMovieRated++;
                }
            }
        }
        System.out.println("Total number of movies rated: " + numMovieRated);
    }
}
