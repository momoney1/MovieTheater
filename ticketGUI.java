package Assignment6;
import java.util.Scanner;

import Assignment5.TwoDimRaggedArrayUtility;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.geometry.Orientation;
import javafx.scene.text.Font; 
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ticketGUI extends Application{
	static ArrayList<String> tickets;
	final HBox top = new TopBox();
	final MiddleBox middle = new MiddleBox();
	final BottomBox bottom = new BottomBox();
	static MovieTicketManager m = new MovieTicketManager();
	
	public MovieTicketManager getMovieTicketManager() {
		return m;
	}
	public ticketGUI() {
		
	}
		
	
	public void printAllTickets() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("All Tickets");
		alert.getDialogPane().setPrefWidth(700);
		alert.setContentText(tickets.toString());
	}
	
		
	public void start(Stage stage) throws Exception{
		Label title = new Label("XYZ Movie Theater");
		
		bottom.readFile.setOnAction(new EventHandler <ActionEvent>(){
			public void handle(ActionEvent e) {
				try {
				File selectedFile;
				FileChooser chooser = new FileChooser();
				BottomBox bottom = new BottomBox();
				chooser.setTitle("Choose a file to read ticket information from");
				if ((selectedFile = chooser.showOpenDialog(null)) != null) {
					 try {
						 bottom.readFile();
					 }catch(Exception r) {
						 Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("File not found");
					 }
					 
				}
				m.readFile(selectedFile);
				tickets = m.getAllTickets();
				}
				catch(FileNotFoundException f) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("File not found");
				}
			}
		});
		
		//ticket type buttons
		middle.ticketTypeButtons[0].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.ticketType = "ADULT";
				middle.idBox.setVisible(false);
				
			}
		});
		middle.ticketTypeButtons[1].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.ticketType = "CHILD";
				middle.idBox.setVisible(false);
			}
		});
		middle.ticketTypeButtons[2].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.idLabel.setText("Employee Id:");
				middle.idBox.setSpacing(39);
				middle.idBox.setVisible(true);
				middle.ticketType = "EMPLOYEE";
			}
		});
		middle.ticketTypeButtons[3].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.idLabel.setText("MoviePass Id:");
				middle.idBox.setSpacing(35);
				middle.idBox.setVisible(true);
				middle.ticketType = "MOVIEPASS";
			}
		});
		
		//ratings buttons
		middle.ratingButtons[0].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.rating = "G";
				
			}
		});
		middle.ratingButtons[1].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.rating = "PG";
				
			}
		});
		middle.ratingButtons[2].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				if(middle.ticketType.equalsIgnoreCase("CHILD")) {
					Alert childAlert = new Alert(AlertType.ERROR);
					childAlert.setContentText("CHILD ticket can only have a rating of G or PG");
					childAlert.showAndWait();
				}
				middle.rating = "PG13";
				
			}
		});
		middle.ratingButtons[3].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				if(middle.ticketType.equalsIgnoreCase("CHILD")) {
					Alert childAlert = new Alert(AlertType.ERROR);
					childAlert.setContentText("CHILD ticket can only have a rating of G or PG");
					childAlert.showAndWait();
				}
				middle.rating = "R";
				
			}
		});
		middle.ratingButtons[4].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				if(middle.ticketType.equalsIgnoreCase("CHILD")) {
					Alert childAlert = new Alert(AlertType.ERROR);
					childAlert.setContentText("CHILD ticket can only have a rating of G or PG");
					childAlert.showAndWait();
				}
				middle.rating = "NR";
				
			}
		});
		
		
		middle.formatButtons[0].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.format = "IMAX";
				
			}
		});
		middle.formatButtons[1].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.format = "THREE_D";
				
			}
		});
		middle.formatButtons[2].setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				middle.format = "NONE";
				
			}
		});
		   
		    
		middle.nameField.setOnAction(new EventHandler<ActionEvent>() {
				 public void handle(ActionEvent e) {
					 try {
						middle.movieName = middle.nameField.getText();
						
					}catch(RuntimeException r) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("name field must contain a value");
						alert.showAndWait();
					}
				 }
			 });	 
		middle.dayField.setOnAction(new EventHandler<ActionEvent>() {
				 public void handle(ActionEvent e) {
					 try {
						if(Integer.parseInt(middle.dayField.getText()) > 31 || Integer.parseInt(middle.dayField.getText()) < 1) {
							Alert dayAlert = new Alert(AlertType.ERROR);
							dayAlert.setContentText("Day is not valid (1-31)");
							dayAlert.showAndWait();
						}
						else {
							middle.day = Integer.parseInt(middle.dayField.getText());
						}
						
					}
				 catch(RuntimeException r) {
					 Alert alert = new Alert(AlertType.ERROR);
					 alert.setContentText("Day field must contain a value");
					 alert.showAndWait();
				 }
				}
			 });
		 middle.timeField.setOnAction(new EventHandler<ActionEvent>() {
			  public void handle(ActionEvent e) {
				  try {
				  if(Integer.parseInt(middle.timeField.getText()) >= 8 && Integer.parseInt(middle.timeField.getText()) <= 23) {
					middle.time = Integer.parseInt(middle.timeField.getText());
				  }
				  else {
					  Alert timeAlert = new Alert(AlertType.ERROR);
					  timeAlert.setContentText("Time is not valid (8-23)");
					  timeAlert.showAndWait();
				  }
				}catch(RuntimeException r) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("time field must contain a value");
					alert.showAndWait();
				}
			  }
		  });
		 middle.idField.setOnAction(new EventHandler<ActionEvent>() {
			  public void handle(ActionEvent e) {
				  try {
				  middle.id = Integer.parseInt(middle.idField.getText());
				}
			  catch(RuntimeException r) {
				  Alert alert = new Alert(AlertType.ERROR);
				  alert.setContentText("Id field must have a value");
				  alert.showAndWait();
			  }
			 }
		  });
		  
		//purchase and clear handlers 
		middle.purchase.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				try {
				middle.price = ticketGUI.m.addTicket(middle.movieName, middle.rating, middle.day, middle.time, middle.format, middle.ticketType, middle.id);
				middle.actualPriceLabel.setText("$"+ String.format("%.2f", middle.price));
				middle.actualPriceLabel.setVisible(true);
				middle.actualPriceLabel.setTextFill(Color.web("#FF76a3"));
				middle.monthlySales += middle.price;
				middle.sales.setText("$" + String.format("%.2f",middle.monthlySales));
				}
				catch(RuntimeException r) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("movie rating and movie format type both have to be selected");
					alert.showAndWait();
				}
				
			}
		});
		middle.clear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				middle.actualPriceLabel.setVisible(false);
				middle.ticketTypeButtons[0].setSelected(true);
				for(int i = 0; i < middle.ratingButtons.length; i++) {
					if(middle.ratingButtons[i].isSelected()) {
						middle.ratingButtons[i].setSelected(false);
						break; //break statement for efficiency.  if selected button is found, no need to continue loop
					}
				}
				for(int i = 0; i < middle.ratingButtons.length; i++) {
					if(middle.formatButtons[i].isSelected()) {
						middle.formatButtons[i].setSelected(false);
						break;
					}
				}
				middle.nameField.clear();
				middle.dayField.clear();
				middle.timeField.clear();
				middle.monthlySales = 0.00;
				middle.sales.setText("$" + String.format("%.2f",middle.monthlySales));
				
			}
		});
		
		//bottom handlers
		bottom.readFile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					bottom.readFile();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		bottom.printMonthly.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)  {
				try {
					bottom.printMonthly(ticketGUI.m);
				
				} catch (Exception e1) {
				e1.printStackTrace();
				}
		  }
		});
		bottom.printAll.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)  {
				try {
				bottom.printAll(ticketGUI.m); //add middle.price here 
				
				} catch (Exception e1) {
				e1.printStackTrace();
				}
		  }
		});
		bottom.print3D.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)  {
				try {
				bottom.print3D(ticketGUI.m);
				
				} catch (Exception e1) {
				e1.printStackTrace();
				}
		  }
		});
		
		bottom.printMoviePass.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)  {
				try {
				bottom.printMoviePass(ticketGUI.m);
				System.out.println(ticketGUI.m);
				
				} catch (Exception e1) {
				e1.printStackTrace();
				}
		  }
		});
		//bottom.printMoviePass.setOnAction(new BottomHandler());
		bottom.exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)  {
				try {
					System.exit(0);
				
				} catch (Exception e1) {
				e1.printStackTrace();
				}
		  }
		});
		
		VBox vPane = new VBox(1);
		vPane.setStyle("-fx-border-color: orange");
		vPane.getChildren().addAll(top, middle, bottom);
		Scene s = new Scene(vPane, 630, 840);
		stage.setTitle("Movie Tickets");
		stage.setScene(s);
		stage.show();
	}
	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
}
class TopBox extends HBox{
	Text title = new Text("XYZ Movie Theater");
	Image image = new Image("https://weambassadors.files.wordpress.com/2014/04/xyz_logo_circle-01.png");
	ImageView imageView = new ImageView(image);
	
	public TopBox() {
		title.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
		imageView.setFitHeight(140);
		imageView.setFitWidth(150);
		this.getChildren().addAll(imageView,title);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(1));
	}
}
class MiddleBox extends VBox{
	String ticketType = "ADULT", rating, movieName, format;
	int id, time, day;
	double price, monthlySales;
	String[] ticketTypeLabels = {"ADULT", "CHILD", "EMPLOYEE", "MOVIEPASS"};
	Label nameLabel = new Label("Movie Name");
	Label dayLabel = new Label("Day (1-31)");
	Label timeLabel = new Label("Time (8-23)");
	Label idLabel = new Label("Employee Id:");
	TextField nameField = new TextField();
	TextField dayField = new TextField();
	TextField timeField = new TextField();
	TextField idField = new TextField();
	Label ratingLabel = new Label("Select one:");
	String[] ratingLabels = {"G", "PG", "PG13", "R", "NR"};
	Label formatLabel = new Label("Select one:");
	String[] formatLabels = {"IMAX", "3D", "NONE"};
	final RadioButton[] ticketTypeButtons = new RadioButton[ticketTypeLabels.length];
	RadioButton[] ratingButtons = new RadioButton[ratingLabels.length];
	RadioButton[] formatButtons = new RadioButton[formatLabels.length];
	ToggleGroup ticketTypeGroup = new ToggleGroup();
	ToggleGroup ratingGroup = new ToggleGroup();
	ToggleGroup formatGroup = new ToggleGroup();
	
	Button purchase = new Button("Purchase Ticket");
	Button clear = new Button("Clear");
	
	Label ticketPriceLabel = new Label("Ticket Price");
	Label actualPriceLabel = new Label("$0.00");
	Label monthlySalesLabel = new Label("Monthly Ticket Sales");
	Label sales = new Label("$0.00");
	
	
	HBox ticketTypeBox = new HBox(50);
	HBox nameBox = new HBox(40);
	HBox dayBox = new HBox(55);
	HBox timeBox = new HBox(48);
	HBox idBox = new HBox(40);
	GridPane gridPane = new GridPane();
	
	Label selectOne = new Label("Select One");
	HBox ratingsBox = new HBox(150);
	HBox formatBox = new HBox(200);
	
	HBox purchaseBox = new HBox(30);
	
	HBox salesBox = new HBox(20);
	
	public MiddleBox() {
		this.setStyle("-fx-border-color: gray;");
		this.setPrefWidth(100);
		this.setPrefHeight(100);
		ticketTypeBox.setPadding(new Insets(10));
		actualPriceLabel.setVisible(false);
		
		
		for(int i = 0; i < ticketTypeButtons.length; i++) {
			ticketTypeButtons[i] = new RadioButton(ticketTypeLabels[i]);
			ticketTypeButtons[i].setToggleGroup(ticketTypeGroup);
		}
		ticketTypeBox.setMargin(ticketTypeButtons[0],new Insets(0, 10, 0, 10));
		ticketTypeBox.getChildren().addAll(ticketTypeButtons);
		ticketTypeButtons[0].setSelected(true);
		
		ticketTypeBox.setAlignment(Pos.CENTER);
		nameBox.setAlignment(Pos.CENTER);
		dayBox.setAlignment(Pos.CENTER);
		timeBox.setAlignment(Pos.CENTER);
		idBox.setAlignment(Pos.CENTER);
		//ticketTypeBox.setPadding(new Insets(10));
		
		idBox.setVisible(false);
		
		nameBox.getChildren().addAll(nameLabel, nameField);
		dayBox.getChildren().addAll(dayLabel, dayField);
		timeBox.getChildren().addAll(timeLabel, timeField);
		idBox.getChildren().addAll(idLabel, idField);
		
		
		for(int i = 0; i < ratingButtons.length; i++) {
			ratingButtons[i] = new RadioButton(ratingLabels[i]);
			ratingButtons[i].setToggleGroup(ratingGroup);
		}
		ratingsBox.setAlignment(Pos.TOP_LEFT);
		ratingsBox.setMargin(ratingLabel, new Insets(1, 15, 10, 50));
		ratingsBox.setMargin(ratingButtons[0], new Insets(0, 0, 0, 10));
		ratingsBox.setPadding(new Insets(1));
		ratingsBox.setSpacing(20);
		ratingsBox.getChildren().add(ratingLabel);
		ratingsBox.getChildren().addAll(ratingButtons);
		
		
		
		for(int i = 0; i < formatButtons.length; i++) {
			formatButtons[i] = new RadioButton(formatLabels[i]);
			formatButtons[i].setToggleGroup(formatGroup);
		}
		formatBox.setAlignment(Pos.TOP_LEFT);
		formatBox.setSpacing(25);
		formatBox.setPadding(new Insets(40));
		formatBox.setMargin(formatLabel, new Insets(1, 10, 10, 10));
		formatBox.setMargin(formatButtons[0], new Insets(0, 10, 0, 10));
		formatBox.getChildren().add(formatLabel);
		formatBox.getChildren().addAll(formatButtons);
		
		purchaseBox.setAlignment(Pos.TOP_CENTER);
		purchaseBox.setPadding(new Insets(20));
		purchaseBox.getChildren().addAll(purchase, clear);

		salesBox.setPadding(new Insets(5, 10, 80, 10));
		salesBox.setSpacing(30);
		salesBox.setAlignment(Pos.TOP_CENTER);
		salesBox.getChildren().addAll(ticketPriceLabel, actualPriceLabel, monthlySalesLabel, sales);
		  
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(20, 10, 50, 10));
		gridPane.addColumn(1,nameBox, dayBox, timeBox, idBox);
		
		getChildren().addAll(ticketTypeBox, gridPane, ratingsBox, formatBox, purchaseBox, salesBox);
	
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
class BottomBox extends VBox{
	HBox upperBox = new HBox(10);
	HBox lowerBox = new HBox(10);
	ArrayList<String> listOfTickets;
	Button readFile = new Button("Read File");
	Button printMonthly = new Button("Print Monthly Sales Report");
	Button printAll = new Button("Print All Tickets");
	Button print3D = new Button("Print 3D Tickets");
	Button printMoviePass = new Button("Print MoviePass Tickets");
	Button exit = new Button("Exit");
	
	
	public void readFile() throws Exception { //reads file, displays monthly total sales

		FileChooser chooser = new FileChooser();
		ArrayList<String> innerList = new ArrayList<>();
		chooser.setTitle("Choose a file to read information from");
		File selectedFile = chooser.showOpenDialog(null);
		if (selectedFile != null) {
			ticketGUI.m.readFile(selectedFile);
		}
		
		Alert al = new Alert(AlertType.INFORMATION);
		al.setTitle("All tickets");
		al.getDialogPane().setPrefWidth(700);
		al.setHeaderText(null);
		al.setContentText(ticketGUI.m.getAllTickets().toString().replace("[", " ").replace("]", "").replaceAll(",", "\n"));
		
		al.showAndWait();
		
	}
	public void printMonthly(MovieTicketManager m) throws Exception {
		String pMonthly = m.monthlySalesReport();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Monthly Sales Report");
		alert.getDialogPane().setPrefWidth(700);
		alert.setHeaderText(null);
		alert.setContentText(pMonthly.replace("[", " ").replace("]", "").replaceAll(",", "\n"));
		
		alert.showAndWait();
	}
	
	public void printAll(MovieTicketManager m) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("All Tickets");
		alert.setResizable(true);
		alert.getDialogPane().setPrefWidth(700);
		alert.setHeaderText(null);
		alert.setContentText(m.getAllTickets().toString().replace("[", " ").replace("]", "").replaceAll(",", "\n")); //wrong values here
		alert.showAndWait();
	}
	
	public void print3D(MovieTicketManager m) {
		String threeD = m.get3DTickets().toString();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("3D Tickets");
		alert.getDialogPane().setPrefWidth(700);
		alert.setHeaderText(null);
		alert.setContentText(threeD.replace("[", " ").replace("]", "").replaceAll(",", "\n"));
		
		alert.showAndWait();
	}
	public void printMoviePass(MovieTicketManager m) {
		String mPass = m.getMoviePassTickets().toString();
		System.out.print(mPass);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("MoviePass Tickets");	
		alert.getDialogPane().setPrefWidth(700);
		alert.setHeaderText(null);
		alert.setContentText(mPass.replace("[", " ").replace("]", "").replaceAll(",", "\n"));
		
		alert.showAndWait();
	}
	
	public BottomBox () {
		this.setSpacing(2);
		this.setPadding(new Insets(10));
		upperBox.getChildren().addAll(readFile, printMonthly, printAll, print3D);
		lowerBox.getChildren().addAll(printMoviePass, exit);
		upperBox.setAlignment(Pos.CENTER);
		lowerBox.setAlignment(Pos.CENTER);
		this.setMargin(upperBox, new Insets(5, 10, 10, 10));
		getChildren().addAll( upperBox, lowerBox);
	}
}
