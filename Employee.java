package Assignment6;
import java.util.Random;

public class Employee extends Ticket{
	/**
	 * the tax amount of a ticket
	 */
	private static final double TAX = 0.096;
	/**
	 * the id value
	 */
	private int ID;
	/**
	 * a boolean value to represent whether or not more than two movies have been watched by this employee
	 */
	private boolean moreThanTwo;
	/**
	 * the prive value
	 */
	private double price;
	/**
	 * A method that returns the price
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * no arg constructor
	 */
	public Employee() {
		
	}
	
	/**
	 * A constructor that initializes field values via calling of super class constructor
	 * @param movieName
	 * @param rating
	 * @param day
	 * @param time
	 * @param feature
	 * @param ID
	 */
	public Employee(String movieName, String rating, int day, int time, Format feature, int ID) {
		super(movieName, rating, day, time, feature, "EMPLOYEE");
		this.ID = ID;
	}
	
	/**
	 * a method that returns the id value
	 * @return ID
	 */
	public int getId() {
		return ID;
	}
	/**
	 * A method that sets the ID value
	 * @param ID
	 */
	public void setId(int ID) {
		this.ID = ID;
	}
	/**
	 * A method that calculates the price of ticket
	 * @return price
	 */
	public double calculateTicketPrice() {
		double price = 0;
		if(moreThanTwo == true) {
			if(getTime() < 18) {
				price = 5.25 * (TAX + 1);
			}
			else {
				price = 6.75 * (TAX + 1);
			}
		}
		
		ticketPrice = price;
		return ticketPrice;
	}
	/**
	 * A method that returns the value of moreThanTwo
	 * @return moreThanTwo
	 */
	public boolean hasWathedTwo() {
		return moreThanTwo;
	}
	/**
	 * A method that sets the value of moreThanTwo
	 * @param m
	 */
	public void setMoreThanTwo (boolean m){
		moreThanTwo = m;
	}
	/**
	 * A method that returns the string representation of an employee type ticket
	 * @return string
	 */
	public String toString() {
		if(getFormat() == Format.NONE)
			return getTicketType() + "-" + ID + super.toString() + " " + String.format("%.2f", ticketPrice);
			else return getTicketType() + "-" + ID + " " + getFormat() + " " +  super.toString() + " " + String.format("%.2f", ticketPrice);
	}
}
