package Assignment6;

public class Adult extends Ticket {
	/**
	 * The tax amount
	 */
	private final double TAX = 0.096;
	/**
	 * The price amount
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
	 * A no arg constructor
	 */
	public Adult() {
	}
	/**
	 * A constructor that accepts arguments and calls superclass constructor to initialize field values
	 * @param movieName
	 * @param rating
	 * @param day
	 * @param time
	 * @param feature
	 */
	public Adult(String movieName, String rating, int day, int time, Format feature) {
		super(movieName, rating, day, time, feature, "ADULT");
		ticketPrice = calculateTicketPrice();
		
	}

	/**
	 * A method that calculates the price 
	 * @return price
	 */
	public double calculateTicketPrice() {
		String s = "";
		double price = 0;
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
		return Double.parseDouble(String.format("%.2f", ticketPrice));
	}
	
	/**
	 * A method that returns the id
	 * @return -1
	 */
	public int getId() {
		return -1;
	}
	
	/**
	 * A method that returns the string representation of an adult type ticket
	 */
	public String toString() {
		if(this.getFormat() == Format.NONE) {
			//System.out.println("doing this from adult class " + ticketPrice);
		return getTicketType() + " "+  super.toString() + String.format("%.2f", ticketPrice);
		}
		else {
			//System.out.println("doing this from adult three_D class class " + ticketPrice);
			return getTicketType() + " " + getFormat() + " " +  super.toString() + String.format("%.2f", ticketPrice);
		}
		//"$" + String.format("%.2f",middle.monthlySales)
	}

}
