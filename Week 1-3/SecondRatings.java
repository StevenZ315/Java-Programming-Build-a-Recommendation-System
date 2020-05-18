
/**
 * Write a description of SecondRatings here.
 * 
 * @author StevenZ
 * @version 05/15/20
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
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
    
    private double getAverageByID(String id, int minimalRaters) {
        double totalRating = 0;
        int count = 0;
        
        for (Rater r : myRaters) {
            if (r.getRating(id) != -1) {
                totalRating += r.getRating(id);
                count += 1;
            }
        }
        
        if (count < minimalRaters) {
            return 0.0;
        } else {
            return totalRating/count;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            String movieID = m.getID();
            double avgRating = getAverageByID(movieID, minimalRaters);
            if (avgRating != 0.0){
                ret.add(new Rating(movieID, avgRating));
            }
        }
        return ret;
    }
                
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "Movie not found for id=" + id;
    }
        
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}
