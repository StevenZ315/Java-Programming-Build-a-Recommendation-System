
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author StevenZ
 * @version 05/15/20
 */

import java.util.*;

public class MovieRunnerAverage {
    private String moviefile = "data/ratedmoviesfull.csv";
    private String ratingsfile = "data/ratings.csv";
    public void printAverageRating() {
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of raters: " + sr.getRaterSize());
        
        //System.out.println(sr.getAverageRatings(2));
        
        //System.out.println(sr.getTitle("2"));
        
        int minimalRaters = 3;
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        System.out.println(avgRatingList.size());
        Collections.sort(avgRatingList);
        for(Rating r:avgRatingList){
            System.out.println(r.getValue()+ "\t" + sr.getTitle(r.getItem()));
        }  
        
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        String title = "The Godfather";
        int minimalRaters = 1;
        
        String id = sr.getID(title);
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        if (id.equals(new String("NO SUCH TITLE"))) {
            System.out.println(id);
        } else {
            for(Rating r : avgRatingList) {
                if(r.getItem().equals(id)) {
                    System.out.println("Average rating for '" + title + "' is: " + r.getValue());
                }

            }
        }
    }
    
}
