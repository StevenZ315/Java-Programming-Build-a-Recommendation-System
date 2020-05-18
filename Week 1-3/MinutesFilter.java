
/**
 * Write a description of MinutesFilter here.
 * 
 * @author StevenZ
 * @version 05/15/20
 */
public class MinutesFilter implements Filter{
    private int min;
    private int max;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        min = minMinutes;
        max = maxMinutes;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
    
    public String getName() {
        return "MinutesFilter[" + min + ", " +"]";
    }
}