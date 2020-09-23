package Assignment6;

/**
 * An abstract class that is the base class of all ticket types
 * @author Moe Diakite
 *
 */
public abstract class Ticket {
	/**
	 * A string that represents the name of the movie
	 */
	private String movieName;
	/**
	 * An integer that represents the day the ticket was purchased
	 */
	private int day;
	/**
	 * an integer that represents the time the ticket was purchased
	 */
	private int time;
	/**
	 * A string that represents the rating of the movie
	 */
	private String rating;
	/**
	 * An integer that represents the id value
	 */
	private int ID;
	/**
	 * An enum type object that represents the format type of the movie
	 */
	private Format feature = Format.NONE;
	/**
	 * A string that represents the type of ticket
	 */
	private String ticketType;
	/**
	 * A double that represents the price of the ticket
	 */
	protected double ticketPrice;
	/**
	 * A no arg constructor
	 */
	public Ticket() {
		
	}
	/**
	 * A constructor that initializes the field values of the class
	 * @param movieName
	 * @param rating
	 * @param day
	 * @param time
	 * @param feature
	 * @param ticketType
	 */
	public Ticket(String movieName, String rating, int day, int time, Format feature, String ticketType) {
		this.movieName = movieName;
		this.day = day;
		this.time = time;
		this.feature = feature;
		this.rating = rating;
		this.ticketType = ticketType;
	}
	/**
	 * An abstract method that will be implemented by each of the subclasses
	 * @return price
	 */
	public abstract double calculateTicketPrice();
	/**
	 * An abstract method for returning id values
	 * @return id
	 */
	public abstract int getId();
	
	/**
	 * A method that returns the name of the movie on the ticket
	 * @returnmovie name
	 */
	public String getName() {
		return movieName;
	}
	/**
	 * A method that sets the name of the movie
	 * @param movieName
	 */
	public void setName(String movieName) {
		this.movieName = movieName;
	}
	/**
	 * A method that returns the time the ticket was purchased
	 * @return  time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * A method that returns the day the ticket was purchased
	 * @return day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * A method that sets the day 
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * A method that sets the time
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * A method that returns the format type
	 * @return feature
	 */
	public Format getFormat() {
		return feature;
	}
	/**
	 * A method that sets the format type
	 * @param feature
	 */
	public void setFormat(Format feature) {
		this.feature = feature;
	}
	/**
	 * A method that returns the rating
	 * @return rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * A method that sets the rating
	 * @param rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	/**
	 * A method that returns the ticket type
	 * @return ticketType
	 */
	public String getTicketType() {
		return ticketType;
	}
	
	/**
	 * A method that returns the string representation of a ticket object
	 */
	public  String toString() {
		return "Movie: " + movieName + " Rating: " + rating + " Day: " + day + " Time: " + time + " Price: $ ";
	}
	
	
}

