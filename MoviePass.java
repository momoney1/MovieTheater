package Assignment6;
import java.util.Random;

/**
 * A class that represents tickets that were purchased with a MoviePass
 * @author Moe Diakite
 *
 */
public class MoviePass extends Ticket{
	/**
	 * the tax amount
	 */
	private static final double TAX = 0.096;
	/**
	 * a boolean that represents whether or not a particular movie has been seen before or not
	 */
	private boolean seen;
	/**
	 * A boolean that represents whether or not this ticket holder has visited this theather before
	 */
	private boolean visited;
	/**
	 * the id value
	 */
	private int ID;
	/**
	 * number of movies watched today by this ticket holder
	 */
	private int numOfMoviesToday;
	/**
	 * A method that returns the price
	 * @return ticketPrice
	 */
	public double getPrice() {
		return ticketPrice;
	}
	
	/**
	 * no arg constructor
	 */
	public MoviePass() {
		
	}
	
	/**
	 * A constructor that initializes field values
	 * @param movieName
	 * @param rating
	 * @param day
	 * @param time
	 * @param feature
	 * @param ID
	 * @param seen
	 */
	public MoviePass(String movieName, String rating, int day, int time, Format feature, int ID, boolean seen) {
		super(movieName, rating, day, time, feature, "MOVIEPASS");
		this.ID = ID;
	}
	
	/**
	 * A method that returns the id value
	 * @return ID
	 */
	public int getId() {
		return ID;
	}
	/**
	 * A method that sets the id alue
	 * @param ID
	 */
	public void setId(int ID) {
		this.ID = ID;
	}
	/**
	 * A method that calculates the ticket price
	 * @return price
	 */
	public double calculateTicketPrice() {
		double price = 0;
		if((getFormat() == Format.NONE) == false || seen == true || visited == true) {
			if(getTime() < 18) 
				price = 10.50;
			else 
				price = 13.50;
				
			switch(getFormat()) {
			case IMAX: price += 3.00;
				break;
			case THREE_D: price += 2.50;
			}
			
			price = price * (TAX + 1);
			ticketPrice = price;
			return ticketPrice;
		}
		else if(numOfMoviesToday < 2 ) {
			price = 9.99;
		}
		else {
			price = 0;
		}
		ticketPrice = price;
		return ticketPrice;
	}
	/**
	 * A method that returns the number of movies watched today
	 * @return numOfMoviesToday
	 */
	public int getNumOfMoviesToday() {
		return numOfMoviesToday;
	}
	/**
	 * A method that increments the number of movies watched by 1 
	 */
	public void incrementNumOfMoviesToday() {
		numOfMoviesToday++;
	}
	/**
	 * A method that sets the number of movies watched today
	 * @param n
	 */
	public void setNumOfMoviesToday(int n) {
		numOfMoviesToday = n;
	}
	/**
	 * A method that returns the value of the boolean visited
	 * @return visited
	 */
	public boolean hasVisited() {
		return visited;
	}
	/**
	 * A method that sets the value of visited to true
	 */
	public void setVisitedToTrue() {
		visited = true;
	}
	
	/**
	 * A method that returns the string representation of a MoviePass type ticket
	 */
	public String toString() {
		if(getFormat() == Format.NONE)
			return getTicketType() + "-" + ID + super.toString() + String.format("%.2f", ticketPrice);
			else return getTicketType() + "-" + ID + " " + getFormat() + " " +  super.toString() + String.format("%.2f", ticketPrice);
	}
	

}
