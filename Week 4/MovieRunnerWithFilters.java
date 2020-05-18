
/**
 * Write a description of MOvieRunnerWithFilters here.
 * 
 * @author StevenZ
 * @version 05/15/20
 */

import java.util.*;

public class MovieRunnerWithFilters {
    private String ratingsfile = "data/ratings_short.csv";
    private String moviefile = "ratedmovies_short.csv";
    
    public void printAverageRating() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatings(minimalRaters);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
        }  
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByYear() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter yf = new YearAfterFilter(2000);
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, yf);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\tYear: " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByGenre() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter gf = new GenreFilter("Crime");
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, gf);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByMinutes() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter mf = new MinutesFilter(110, 170);
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, mf);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + "Time: " + MovieDatabase.getMinutes(r.getItem()) + " Mins" + "\t" + MovieDatabase.getTitle(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByDirector() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter df = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, df);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1980));
        f.addFilter(new GenreFilter("Romance"));
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, f);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\tYear: " + MovieDatabase.getYear(r.getItem()) + "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
        System.out.println("========================================================");
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        AllFilters f = new AllFilters();
        f.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        f.addFilter(new MinutesFilter(30, 170));
        
        System.out.println("Number of raters: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        int minimalRaters = 1;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, f);
        System.out.println("found " + avgRatingList.size() + " movies");
        Collections.sort(avgRatingList);
        for(Rating r : avgRatingList){
            System.out.println(r.getValue()+ "\tTime: " + MovieDatabase.getMinutes(r.getItem()) + " Mins\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
        System.out.println("========================================================");
    }
}
