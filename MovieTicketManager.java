package Assignment6;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.Collections.*;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import Assignment6.Ticket;
import javafx.scene.control.Alert;

/**
 * A class called MovieTicketManager to manage the ticket sales of a movie theater
 * @author Moe Diakite
 *
 */
public class MovieTicketManager implements MovieTicketManagerInterface{
	/**
	 * an arrayList of tickets
	 */
	private ArrayList<Ticket> ticketList = new ArrayList<>();
	
	/**
	 * no arg constructor
	 */
	public MovieTicketManager() {
		
	}
	
	/**
	 * A method that adds a ticket into the ticketList and returns the calculated price of the ticket
	 * @param movieN
	 * @param rating
	 * @param d
	 * @param t
	 * @param f
	 * @param type
	 * @param id
	 * @return price
	 * 
	 */
	public double addTicket(String movieN, String rating, int d, int t, String f, String type, int id) {
		Adult a;
		Child c;
		Employee e;
		MoviePass p;
		if(type.equalsIgnoreCase("MOVIEPASS")) {
			switch(f) {
			case "IMAX": 
				if(numThisMovie(id, movieN) >= 1) {
					p = new MoviePass(movieN, rating, d, t, Format.IMAX, id, true);
					
				} else {
					p = new MoviePass(movieN, rating, d, t, Format.IMAX, id, false);
				}
				if(numVisits(id) > 0) {
					p.setVisitedToTrue();
					
				}
				ticketList.add(p);
				return p.calculateTicketPrice();
			case "THREE_D": 
				if(numThisMovie(id, movieN) >= 1)
				p = new MoviePass(movieN, rating, d, t, Format.THREE_D, id, true);
				else {
					p = new MoviePass(movieN, rating, d, t, Format.THREE_D, id, false);
				}
				if(numVisits(id) > 0) {
					p.setVisitedToTrue();
				}
				ticketList.add(p);
				return p.calculateTicketPrice();
			case "NONE": 
				if(numMoviesToday(id, d) >= 1) {
					p = new MoviePass(movieN, rating, d, t, Format.NONE, id, true);
					
				}
				else {
						p = new MoviePass(movieN, rating, d, t, Format.NONE, id, false);
						
					}
				if(numVisits(id) > 0) {
						p.setVisitedToTrue();
					}
					ticketList.add(p);
					return p.calculateTicketPrice();
			default: return 0;
				
			}		
		}else if(type.equalsIgnoreCase("employee")) {
			switch(f) {
			
			case "IMAX": 
				e = new Employee(movieN, rating, d, t, Format.IMAX, id);
				if(numVisits(id) >= 2)
					e.setMoreThanTwo(true);
				ticketList.add(e);
			return e.calculateTicketPrice();
		case "THREE_D": 
			e = new Employee(movieN, rating, d, t, Format.THREE_D, id);
			if(numVisits(id) >= 2)
				e.setMoreThanTwo(true);
			ticketList.add(e);
			return e.calculateTicketPrice();
		case "NONE": 
			e = new Employee(movieN, rating, d, t, Format.NONE, id);
			if(numVisits(id) >= 2)
				e.setMoreThanTwo(true);
			ticketList.add(e);
			return e.calculateTicketPrice();
		default: return 0;	
			}
		
		}
		else if(type.equalsIgnoreCase("adult")) {
			switch(f) {
			case "IMAX": 
				a = new Adult(movieN, rating, d, t, Format.IMAX);
				ticketList.add(a);
				return a.calculateTicketPrice();
			case "THREE_D": 
				a = new Adult(movieN, rating, d, t, Format.THREE_D);
				ticketList.add(a);
				return a.calculateTicketPrice();
			case "NONE":
				a = new Adult(movieN, rating, d, t, Format.NONE);
				ticketList.add(a);
				return a.calculateTicketPrice();
			}
		}else if(type.equalsIgnoreCase("child")) {
			if(rating.equalsIgnoreCase("G") == true || rating.equalsIgnoreCase("PG") == true) {
			switch(f) {
			case "IMAX": 
				c = new Child(movieN, rating, d, t, Format.IMAX);
				ticketList.add(c);
				return c.calculateTicketPrice();
			case "THREE_D": 
				c = new Child(movieN, rating, d, t, Format.THREE_D);
				ticketList.add(c);
				return c.calculateTicketPrice();
			case "NONE":
				c = new Child(movieN, rating, d, t, Format.NONE);
				ticketList.add(c);
				return c.calculateTicketPrice();
			}
		  }
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Type is not valid");
		}
		return 0;
	}
	/**
	 * @return an arrayList of 3d tickets in string form
	 */
	public ArrayList<String> get3DTickets() {
		ArrayList<String> threeDList = new ArrayList<>();
		this.sortByDay();
		for(Ticket t : ticketList) {
			if(t.getFormat().toString().equalsIgnoreCase("THREE_D")) {
				threeDList.add(t.toString());
			}
		}
		
		return threeDList;
	}
	/**
	 * @return an arrayList of all the tickets in string form
	 */
	public ArrayList<String> getAllTickets(){
		ArrayList<String> tList = new ArrayList<>();
		this.sortByDay();
		for(Ticket t: ticketList) {
			tList.add(t.toString());
		}
		return tList;
	}
	/**
	 * @return an arrayList of all the moviePass type tickets in string form
	 */
	public ArrayList<String> getMoviePassTickets(){
		ArrayList<String> moviePass = new ArrayList<>();
		this.sortById();
		for(Ticket t : ticketList) {
			moviePass.add(t.toString());
		}
		return moviePass;
	}
	/**
	 * @return a string that represents the sales report for the month
	 */
	public String monthlySalesReport() {
		String adultFormat = "", childFormat = "", employeeFormat = "", moviePassformat = "";
		String monthly = "";
		double child = 0, adult = 0, employee = 0, moviePass = 0;
		int numChild = 0, numAdult = 0, numEmployee = 0, numMoviePass = 0;
		for(Ticket t: ticketList) {
			if(t.getTicketType().equalsIgnoreCase("child")) {
				child += t.calculateTicketPrice();
				numChild++;
			}
			else if(t.getTicketType().equalsIgnoreCase("adult")) {
				adult += t.calculateTicketPrice();
				numAdult++;
			}
			else if(t.getTicketType().equalsIgnoreCase("employee")) {
				employee += t.calculateTicketPrice();
				numEmployee++;
			}
			else {
				moviePass += t.calculateTicketPrice();
				numMoviePass++;
			}
		}
		monthly = String.format("       Monthly Sales Report\n\n                        Sales       Number\n"
				+ "ADULT             $%-8.2f %d\n"
				+ "CHILD              $%-8.2f  %d\n"
				+ "EMPLOYEE       $%-8.2f    %d\n"
				+ "MOVIEPASS     $%-8.2f    %d\n\n"
				+ "Total Monthly Sales: $%-7.2f", adult, numAdult, child, numChild, employee, numEmployee, moviePass, numMoviePass, totalSalesMonth());
		
		return monthly;
	}
	
	/**
	 * A method that returns number of movies watched today
	 * @param id
	 * @param date
	 * @return numOfMovies
	 */
	public int numMoviesToday(int id, int date) {
		int numOfMovies = 0;
		for(Ticket t: ticketList) {
			if(t.getDay() == date && t.getId() == id) {
				numOfMovies++;
			}
		}
		return numOfMovies;
	}
	/**
	 * A method that uses id value and movie name to get the number of times this movie has been watched by a particular ticket holder
	 * @param id
	 * @param movie
	 * @return timesSeen
	 */
	public int numThisMovie(int id, String movie) {
		int timesSeen = 0;
		for(Ticket t: ticketList) {
			if(t.getId() == id && t.getName().equalsIgnoreCase(movie)) {
				timesSeen ++;
			}
		}
		return timesSeen;
	}
	/**
	 * A method uses the id value of a ticket to get the number of times this id has frequented this movie theater
	 * @param id
	 * @return numOfVisits
	 */
	public int numVisits(int id) {
		int numOfVisits = 0;
		for(Ticket t: ticketList) {
			if(t.getId() == id) {
				numOfVisits++;
			}
		}
		return numOfVisits;
	}
	/**
	 * A method that reads from a file, and adds the contents of the file into ticketList
	 * @param file
	 * 
	 */
	public void readFile(File file) throws FileNotFoundException{
		Scanner inputFile = new Scanner(file);
		String input = "";
		String[] s;
		String movieName = "";
		int day = 0;
		int time = 0;
		String rating = "";
		int ID = 0;
		String feature = "";
		String ticketType = "";
		double price = 0;
		
		while(inputFile.hasNextLine()) {
			boolean seen = false;
			input = inputFile.nextLine();
			s = input.split(":");
			movieName = s[0];
			rating = s[1];
			if(rating.equalsIgnoreCase("G") != true && rating.equalsIgnoreCase("PG") != true && rating.equalsIgnoreCase("PG13") != true
					&& rating.equalsIgnoreCase("R") != true && rating.equalsIgnoreCase("NR") != true) {
				continue;
			}
			day = Integer.parseInt(s[2]);
			if(day < 1 || day > 31)
				continue;
			time = Integer.parseInt(s[3]);
			if(time < 8 || time > 23)
				continue;
			feature = s[4];
			if(feature.equalsIgnoreCase("IMAX") == false && feature.equalsIgnoreCase("3D") == false && feature.equalsIgnoreCase("NONE") == false)
				continue;
			ticketType = s[5];
			if(ticketType.equalsIgnoreCase("Employee") == false && ticketType.equalsIgnoreCase("moviePass") == false && ticketType.equalsIgnoreCase("child") == false
					&& ticketType.equalsIgnoreCase("Adult") == false)
				continue;
			ID = Integer.parseInt(s[6]);
			price = addTicket(movieName, rating, day, time, feature, ticketType, ID);
		}
		
	}
	private void sortByDay() {
		ArrayList<Integer> dayList = new ArrayList<>();
		ArrayList<Ticket> tempList = new ArrayList<>();
		
		for(Ticket t: ticketList) {
			dayList.add(t.getDay()); 
		}
		Collections.sort(dayList); 
		for(Integer d: dayList) {
			for(int i = 0; i < ticketList.size(); i++) { 
				if(d == ticketList.get(i).getDay()) {
					tempList.add(ticketList.get(i)); 
					ticketList.remove(i);
				} 
			}
		}
		ticketList = tempList;
	}
	private void sortById() {
		ArrayList<Integer> idList = new ArrayList<>();
		ArrayList<Ticket> tempList = new ArrayList<>();
		try {
		for(Ticket t: ticketList) {
			idList.add(t.getId());
		}
		
		Collections.sort(idList);
		for(Integer id: idList) {
			for(Ticket t: ticketList) {
				if(id == t.getId()) {
					tempList.add(t);
					ticketList.remove(t);
					if(ticketList.size() < 1)
						break;
				}
			}
			if(ticketList.size() < 1)
				break;
		}
		ticketList = tempList;
		}catch(RuntimeException r) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("please make sure to press enter after entering id value");
			alert.showAndWait();
		}
	}
	/**
	 * A method that calculates the total ticket sales for the month
	 * @return total
	 */
	public double totalSalesMonth() {
		double total = 0;
		for(Ticket t: ticketList) {
			total += t.calculateTicketPrice();
		}
		return total;
	}
	
	/**
	 * A method that overrides the superClass version and returns a string representation of the ticketList arrayList
	 * @return entireList;
	 */
	public String toString() {
		String entireList = "";
		for(Ticket t: ticketList) {
			entireList += t.toString() + "\n";
		}
		return entireList;
	}
}
