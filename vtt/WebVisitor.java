package vtt;
import java.util.ArrayList;

/**
 * An instance of "WebVisitor" represents a web-site visitor that has not been identified as either an admin
 * or volunteer. When the web visitor has been identified, any instance of "WebVisitor"<br>
 * can convert itself into either:
 * <ul>
 *     <li>"Admin" object via {@link #setAsAdmin()}</li>
 *     <li>"Volunteer" object via {@link #setAsVolunteer()}</li>
 * </ul>
 */
public class WebVisitor {
     //=== FIELDS ===
     //===============================================================================================
     /**
      * Value represents if web-visitor is an admin<br>
      * <br>
      * <b>Values</b><br>
      * TRUE - Web-visitor is admin<br>
      * FALSE - Web-visitor is not admin, or admin statuse is undetermined<br>
      * <br>
      * <b>Remarks:</b><br>
      * The "Admin" object
      */
     public boolean isAdmin = false;
     
     /** Web-site visitor's username. Will have value if web-site visitor is registered */
     public String username;
         
     /** Web-site visitor's password. Will have value if web-site visitor is registered */
     public String password;
     
     //=== ENUMS ===
     //===============================================================================================
     /**
      * Each item represents an admin's database privilege. For security reasons,
      * no item grants or denies the admin power, but instead acts as a descriptor
      * of an admin's privileges in the database
      */
     public static enum Privileges {
    	 SELECT,
    	 INSERT,
    	 UPDATE,
    	 DELETE
     }
     
     //=== CONSTRUCTORS ===
     //===============================================================================================
     /**
      * 0 argument constructor with no side-effects
      */
     public WebVisitor() {}

     //=== SUB CLASSES ===
     //===============================================================================================
     /**
      * A "Volunteer" object represents a volunteer. Each "Volunteer" instance will contain 
      * volunteer entries (see: {@link #volunteerEntries})
      */
     public class Volunteer extends WebVisitor {
         //=== WebVisitor.Volunteer: Fields ===
         //===========================================================================================
         /**
          * Volunteer entries that will be added to the database<br>
          * <br>
          * <b>Remarks:</b><br>
          * ArrayList does not retrieve volunteer entries from the database
          */
         ArrayList<VolunteerEntry> volunteerEntries;
         public String firstName;
         public String lastName;
         public String phoneNumber;
         public String email;
         public String street;
         public String city;
         public String zip;

         //=== WebVisitor.Volunteer: Sub Classes ===
         //===========================================================================================
         class VolunteerEntry {
             /** Date of the volunteer activity */
        	 public String date;
        	 
        	 /**
        	  * Length of the volunteer activity to the nearest 15 minute increment<br>
        	  * <br>
        	  * <b>Example of nearest 15 minute increment:</b><br>
        	  * 1:30, 2:15, 7:00, 3:45<br>
        	  * <br>
        	  * <b>Remarks:</b><br>
        	  * This documentation doesn't show or imply the
        	  * time format for this field
        	  */
        	 public String length;
        	 
        	 /** The type of activity performed by the volunteer */
        	 public String activityType;
         }

         //=== WebVisitor.Volunteer: Constructors ===
         //===========================================================================================
         /**
          * 0 argument constructor with no side-effects
          */
         public Volunteer() { }
         /**
          * Creates a new "Volunteer" object from a "WebVisitor" object
          * @param webVisitor
          */
         public Volunteer(WebVisitor webVisitor) { }
     }

     /**
      * An "Admin" object represents a web-site administrator. Instances of "Admin"
      * can remember an admin's database privileges, but is only remembered for viewing
      * and has no control over what a person can do to a database. Only the database
      * can control the admin's privileges
      */
     public class Admin extends WebVisitor {
         //=== WebVisitor.Admin: Fields ===
         //===========================================================================================
    	 private Privileges[] privileges;
    	     public Privileges[] getPrivileges() { return this.privileges; }

         //=== WebVisitor.Admin: Constructors ===
         //===========================================================================================
         /**
          * 0 argument constructor with no side-effects
          */
    	 public Admin() {} 
    	 /**
    	  * Creates "Admin" object with viewable privileges
    	  * @param privileges Privileges granted to the admin by the database
    	  */
         public Admin(Privileges[] privileges) { this.privileges = privileges; }
    	 /**
    	  * Creates new "Admin" object from a "WebVisitor" object
    	  * @param privileges Privileges granted to the admin by the database
    	  * @param webVisitor "WebVisitor" object being turned into an "Admin" object
    	  */
         public Admin(Privileges[] privileges, WebVisitor webVisitor) { 
        	 this.privileges = privileges;
        	 this.isAdmin = webVisitor.isAdmin;
        	 this.username = webVisitor.username;
        	 this.password = webVisitor.password;
         }
     }

     //=== METHODS ===
     //===============================================================================================
     /**
      * Returns the calling "WebVisitor" object as an "Admin" object. If the calling object is
      * an extension of "WebVisitor", then the calling object will be returned
      * (prevents "Volunteer" objects becoming "Admin" objects and visa versa).
      * The calling object is also returned if it's variable "isAdmin" is false
      * @return
      */
     public WebVisitor toAdmin() {
    	 if(this instanceof Admin) { /* don't change object */ return this; }
    	 if(this instanceof Volunteer) { /* don't change object */ return this; }
    	 if(this.isAdmin)
    		 return new Admin();
    	 else
    		 return this;
     }
     /**
      * Returns the calling "WebVisitor" object as an "Admin" object. If the calling object is
      * an extension of "WebVisitor", then the calling object will be returned
      * (prevents "Volunteer" objects becoming "Admin" objects and visa versa).
      * The calling object is also returned if it's variable "isAdmin" is false
      * @return
      */
     public WebVisitor toAdmin(Privileges[] privileges){
         if(this instanceof Admin) { /* don't change object */ return this; }
         if(this instanceof Volunteer) { /* don't change object */ return this; }
         if(this.isAdmin) 
        	 return new Admin(privileges, this);
         else
        	 return this;
     }

     /**
      * Returns the calling "WebVisitor" object as a "Volunteer" object. If the calling object is
      * an extension of "WebVisitor", then the calling object will be returned
      * (prevents "Volunteer" objects becoming "Admin" objects and visa versa)
      */
     public WebVisitor toVolunteer() {
         if(this instanceof Admin) { /* don't change object */ return this; }
         if(this instanceof Volunteer) { /* don't change object */ return this; }
         return new Volunteer(this);
     }
}