package Assignment6;

/**
 * A class that represents child type tickets
 * @author Moe Diakite
 *
 */
public class Child extends Ticket{
	/**
	 * the tax amount of a ticket
	 */
	private final double TAX = 0.096;
	/**
	 * the price of a ticket
	 */
	private double price;
	
	/**
	 * no arg constructor
	 */
	public Child() {
	}
	
	/**
	 * A constructor that initializes field values via calling of super class constructor
	 * @param movieName
	 * @param rating
	 * @param day
	 * @param time
	 * @param feature
	 */
	public Child(String movieName, String rating, int day, int time, Format feature) {
		super(movieName, rating, day, time, feature, "CHILD");
		ticketPrice = calculateTicketPrice();
		
	}
	
	/**
	 * A method that calculates ticket prices
	 * @return price
	 */
	public double calculateTicketPrice() {
		double price = 0;
		if(getTime() < 18) 
			price = 5.75;
		else 
			price = 10.75;
		
		switch(getFormat()) {
		case IMAX: price += 2;
		     break;
		case THREE_D: price += 1.5;
		}
		price = price * (TAX + 1);
		ticketPrice = price;
		return ticketPrice;
	}
	/**
	 * @return -1
	 */
	public int getId() {
		return -1;
	}
	
	/**
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @return string representation of this object
	 */
	public String toString() {
		if(getFormat() == Format.NONE)
		return getTicketType() + " "+  super.toString() + String.format("%.2f", ticketPrice);
		else return getTicketType() + " " + getFormat() + " " +  super.toString() + String.format("%.2f", ticketPrice);
	}

}
