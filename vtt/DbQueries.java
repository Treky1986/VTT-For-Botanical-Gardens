package vtt;
import java.util.ArrayList;
import vtt.WebVisitor.Volunteer;
import vtt.WebVisitor.Admin;

public class DbQueries {
	public static class WebVisitorQueries {
		public static boolean isWebVisitorAdmin() { return false; }
	}
    public static class VolunteerQueries {
    	/**
    	 * Returns all volunteers with more than [hours] hours for the current calendar year
    	 * @param hours The total hours for the current calendar year that a volunteer must exceed to be
    	 *        included in the query
    	 * @return
    	 */
    	public static Volunteer[] getVolunteersWithMoreThan(int hours) { return new Volunteer[] {}; }
    	/**
    	 * Returns all volunteers with more than [hours] volunteer hours for the current calendar year
    	 * @param hours The total hours for the current calendar year that a volunteer must not exceed to be
    	 *        included in the query
    	 * @return
    	 */
    	public static Volunteer[] getVolunteersWithLessThan(int hours) { return new Volunteer[] {}; }
    	/**
    	 * Returns all volunteers with a total amount of volunteer hours (for the current calendar year) more than
    	 * or equal to [hoursMin] and less than or equal to [hoursMax]
    	 * volunteer hours
    	 * @param hoursMin The minimum amount of total hours for the current calendar year that a volunteer must equal or
    	 *        exceed to be included in the query
    	 * @param hoursMax
    	 *        The maximum amount of total hours for the current calendar year that a volunteer must equal or
    	 *        not exceed to be included in the query
    	 * @return
    	 */
    	public static Volunteer[] getVolunteersBetweenOrEqualTo(int hoursMin, int hoursMax) { return new Volunteer[] {};}
    	
    	public static Volunteer[] getVolunteersWithMoreThan(String startDate, String endDate, int hours) { return new Volunteer[] {};}
    	public static Volunteer[] getVolunteersWithLessThan(String startDate, String endDate, int hours) { return new Volunteer[] {};}
    	public static Volunteer[] getVolunteersBetweenOrEqualTo(String startDate, String endStart, int hoursMin, int hoursMax) { return new Volunteer[] {};}
    }
    public static class AdminQueries {
    	/**
    	 * Gets the database grants for the logged-in admin
    	 * @param admin
    	 * @return
    	 */
    	public static Admin.Privileges[] getGrants(WebVisitor.Admin admin) { 
    		ArrayList<Admin.Privileges> privilegesArrayList = new ArrayList<Admin.Privileges>();
    		/* MySQL: SHOW GRANTS */
    		//TODO: privilegesArrayList <-- admin's database grants (privileges)
    		return (Admin.Privileges[]) privilegesArrayList.toArray();	
    	}
    }
}
