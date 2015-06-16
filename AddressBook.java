package application;
/*
 * Derrick Fox
 * CS 214 - Advanced Java
 * Project 9 - Databases
 * April 20, 2015
 * Java 1.8 JavaFX 2.2
 */

import java.io.*;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class AddressBook extends Application {
  // Specify the size of five string fields in the record
  final static int FIRST_NAME_SIZE = 32;
  final static int MIDDLE_INITIAL_SIZE = 2;
  final static int LAST_NAME_SIZE = 32;
  final static int STREET_SIZE = 32;
  final static int CITY_SIZE = 20;
  final static int STATE_SIZE = 2;
  final static int ZIP_SIZE = 5;
  final static int TELEPHONE_SIZE = 10;
  final static int ID_SIZE = 32;
  final static int RECORD_SIZE = (FIRST_NAME_SIZE + MIDDLE_INITIAL_SIZE + LAST_NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE + TELEPHONE_SIZE + ID_SIZE);
	private static String firstname = null;
	private static String mi = null;
	private static String lastname = null;
	private static String street = null;
	private static String city = null;
	private static String state = null;
	private static String zip = null;
	private static String email = null;
	private static String id = null;
	private static String telephone = null;

  // Access address.dat using RandomAccessFile
  private RandomAccessFile raf;

  // Text fields
  private TextField tfFirstName = new TextField();
  private TextField tfMiddleInitial = new TextField();
  private TextField tfLastName = new TextField();
  private TextField tfStreet = new TextField();
  private TextField tfCity = new TextField();
  private TextField tfState = new TextField();
  private TextField tfEmail = new TextField();
  private TextField tfID = new TextField();
  private TextField tfTelephone = new TextField();

  // Buttons
  private Button btAdd = new Button("Insert");
  private Button btFirst = new Button("First");
  private Button btNext = new Button("Next");
  private Button btPrevious = new Button("Previous");
  private Button btLast = new Button("Last");
  private Button btUpdate = new Button("Update");
  private Button btView = new Button("View");
  private Button btClear = new Button("Clear");
 

  public AddressBook() {
    // Open or create a random access file
    try {
      raf = new RandomAccessFile("address.dat", "rw");
    }
    catch(IOException ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
  
  @Override
  public void start(Stage primaryStage) throws IOException, SQLException {
	String url = "jdbc:mysql://localhost/javabook";
  	String user = "scott";
  	String password = "tiger";
    Connection myConn = DriverManager.getConnection(url, user, password);
    Statement myStmt = myConn.createStatement();
    
    // Book Table //////////////////////////////////////////////////////////////////
    String sqlDropBookTable = "drop table if exists Staff;";
    myStmt.executeUpdate(sqlDropBookTable);
	String sqlCreateBookTable = 
					  "create table Staff (" +
					  "id char(9) not null," +
					  "lastName varchar(15)," +
					  "firstName varchar(15)," +
					  "mi char(1)," +
					  "address varchar(20)," +
					  "city varchar(20)," +
					  "state char(2)," +
					  "telephone char(10)," +
					  "email varchar(40)," +
					  "primary key (id)" +
					  ");";
	myStmt.executeUpdate(sqlCreateBookTable);
	
	String sql1 = 
			"INSERT INTO Staff " +
  			//"(id = '1', lastName = 'Tester', firstName = 'Mister', mi = 'M', address = 'Tester Street', city = 'TesterTown', state = 'NJ', telephone ='12345678', email = 'email@eami.com')";
		    "(id, lastName, firstName, mi, address, city, state, telephone, email)" +
  			//"values(?,?,?,?,?,?,?,?,?)";
  			"values('1', 'Hatch', 'Robin', 'M', 'First Street', 'Silver Spring', 'NJ', '283949', 'robin@email.com')";
	myStmt.executeUpdate(sql1);
	String sql2 = 
			"INSERT INTO Staff " +
  			//"(id = '1', lastName = 'Tester', firstName = 'Mister', mi = 'M', address = 'Tester Street', city = 'TesterTown', state = 'NJ', telephone ='12345678', email = 'email@eami.com')";
		    "(id, lastName, firstName, mi, address, city, state, telephone, email)" +
  			//"values(?,?,?,?,?,?,?,?,?)";
  			"values('2', 'Banks', 'DeAndre', 'T', 'Second Street', 'Washington', 'DC', '283949', 'deandre@email.com')";
	myStmt.executeUpdate(sql2);
	String sql3 = 
			"INSERT INTO Staff " +
  			//"(id = '1', lastName = 'Tester', firstName = 'Mister', mi = 'M', address = 'Tester Street', city = 'TesterTown', state = 'NJ', telephone ='12345678', email = 'email@eami.com')";
		    "(id, lastName, firstName, mi, address, city, state, telephone, email)" +
  			//"values(?,?,?,?,?,?,?,?,?)";
  			"values('3', 'Bell', 'Olivia', 'A', 'Third Street', 'Chefsville', 'NJ', '483949', 'olivia@email.com')";
	myStmt.executeUpdate(sql3);
	myConn.close();

    VBox p1 = new VBox(5);
    p1.setAlignment(Pos.TOP_LEFT);
    p1.getChildren().addAll(
    		new Label("ID"),
    		tfID,
    		new Label("First Name"), 
    		tfFirstName, 
    		new Label("M."), 
    		tfMiddleInitial, 
    		new Label("Last Name"), 
    		tfLastName,
    		new Label("Street"),
    		tfStreet,
    		new Label("City"),
    		tfCity,
    		new Label("State"),
    		tfState,
    		new Label("Email"),
    		tfEmail,
    		new Label("Telephone"),
    		tfTelephone
    		);
        
    // Add buttons to a pane
    HBox p3 = new HBox(5);
    p3.getChildren().addAll(btAdd, btFirst, btNext, btPrevious,
      btLast, btUpdate); //, btClear, btView);
    HBox p4 = new HBox(5);
    p4.getChildren().addAll(btClear, btView);
    p4.setAlignment(Pos.CENTER);
    p3.setAlignment(Pos.CENTER);
    
    // Add p1 and p3 to a border pane
    VBox borderPane = new VBox(5);
    borderPane.getChildren().addAll(p1, p3, p4);
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 350, 550);
    primaryStage.setTitle("Staff Database"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // Display the first record if exists
    try {
      if (raf.length() > 0) readAddress(0);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    
    // Event Listeners
    btUpdate.setOnAction(e -> {
    	try {
    		updateAddress(raf.length());
    	}
    	catch (Exception ex) {
    	}
    });
    btAdd.setOnAction(e -> {
      try {
        writeAddress(raf.length());
      }
      catch (Exception ex) {
      }
    }); 
    btFirst.setOnAction(e -> {
      try {
        if (raf.length() > 0) readAddress(0);
      }
      catch (IOException ex) {   
      }
    });
    btNext.setOnAction(e -> {
      try {
      long currentPosition = raf.getFilePointer();
      if (currentPosition < raf.length())
        readAddress(currentPosition);
      }
      catch (IOException ex) {
        
      }
    });
    btPrevious.setOnAction(e -> {
      try {
        long currentPosition = raf.getFilePointer();
        if (currentPosition -  2 * RECORD_SIZE > 0)
          // Why 2 * 2 * RECORD_SIZE? See the follow-up remarks
          readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
        else
          readAddress(0);
      }
      catch (IOException ex) {
      }
    });
    btLast.setOnAction(e -> {
      try {
        long lastPosition = raf.length();
        if (lastPosition > 0)
          // Why 2 * RECORD_SIZE? See the follow-up remarks
          readAddress(lastPosition - 2 * RECORD_SIZE);
      }
      catch (IOException ex) {
      }
    });
    btClear.setOnAction(e -> {
    	tfFirstName.clear();
    	tfMiddleInitial.clear();
    	tfLastName.clear();
    	tfStreet.clear();
    	tfCity.clear();
    	tfState.clear();
        tfEmail.clear();
        tfTelephone.clear();
        tfID.clear();
      });
    btView.setOnAction(e -> {
        try {
        	viewAddress(raf.length());
            }
        catch (Exception ex) {
        }
      });
  }

  /** Write a record at the end of the file 
 * @throws SQLException */
  public void writeAddress(long position) throws SQLException {
    try {
      raf.seek(position);
      FixedLengthStringIO.writeFixedLengthString(
        tfFirstName.getText(), FIRST_NAME_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
    	        tfMiddleInitial.getText(), MIDDLE_INITIAL_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
    	        tfLastName.getText(), LAST_NAME_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
        tfStreet.getText(), STREET_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
        tfCity.getText(), CITY_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
        tfState.getText(), STATE_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
        tfEmail.getText(), ZIP_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
    	        tfTelephone.getText(), TELEPHONE_SIZE, raf);
      FixedLengthStringIO.writeFixedLengthString(
    	        tfID.getText(), ID_SIZE, raf);
      
      /////////////////////////////////////////////////////////////////////////
    
    String url = "jdbc:mysql://localhost/javabook";
  	String user = "scott";
  	String password = "tiger";

    Connection myConn = DriverManager.getConnection(url, user, password);
    String sql = "insert into Staff "
  			+ "(id, lastName, firstName, mi, address, city, state, telephone, email)"
  			+ "values(?,?,?,?,?,?,?,?,?)";
    PreparedStatement myStmt = myConn.prepareStatement(sql);
    myStmt.setString(1, tfID.getText());
    myStmt.setString(2, tfLastName.getText());
    myStmt.setString(3, tfFirstName.getText());
    myStmt.setString(4, tfMiddleInitial.getText());
    myStmt.setString(5, tfStreet.getText());
    myStmt.setString(6, tfCity.getText());
    myStmt.setString(7, tfState.getText());
    myStmt.setString(8, tfTelephone.getText());
    myStmt.setString(9, tfEmail.getText());

  	myStmt.executeUpdate();
  	
  	System.out.println("Insert Complete.");
  	
    myConn.close();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  public void viewAddress(long position) throws SQLException {
	    try {
	      raf.seek(position);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfFirstName.getText(), FIRST_NAME_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfMiddleInitial.getText(), MIDDLE_INITIAL_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfLastName.getText(), LAST_NAME_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfStreet.getText(), STREET_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfCity.getText(), CITY_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfState.getText(), STATE_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfEmail.getText(), ZIP_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfTelephone.getText(), TELEPHONE_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfID.getText(), ID_SIZE, raf);
	      
	      /////////////////////////////////////////////////////////////////////////
	    
	    String url = "jdbc:mysql://localhost/javabook";
	  	String user = "scott";
	  	String password = "tiger";

	    Connection myConn = DriverManager.getConnection(url, user, password);
	   
	    String sql ="select * from Staff where id = '" + tfID.getText() + "';";
        PreparedStatement myStmt = myConn.prepareStatement(sql);   
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
        	//Retrieve by column name
           id  = rs.getString(1);
           lastname = rs.getString(2);
           firstname = rs.getString(3);
           mi = rs.getString(4);
           street = rs.getString(5);
           city = rs.getString(6);
           state = rs.getString(7);
           telephone = rs.getString(8);
           email = rs.getString(9);
        }
        
        tfFirstName.setText(firstname);
        tfMiddleInitial.setText(mi);
        tfLastName.setText(lastname);
        tfStreet.setText(street);
        tfCity.setText(city);
        tfState.setText(state);
        tfEmail.setText(email);
        tfTelephone.setText(telephone);
        tfID.setText(id);
        
        System.out.println("View Button Pressed");
        myConn.close();
	      
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	  }
  
  public void updateAddress(long position) throws SQLException {
	    try {
	      raf.seek(position);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfFirstName.getText(), FIRST_NAME_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfMiddleInitial.getText(), MIDDLE_INITIAL_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfLastName.getText(), LAST_NAME_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfStreet.getText(), STREET_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfCity.getText(), CITY_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfState.getText(), STATE_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	        tfEmail.getText(), ZIP_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfTelephone.getText(), TELEPHONE_SIZE, raf);
	      FixedLengthStringIO.writeFixedLengthString(
	    	        tfID.getText(), ID_SIZE, raf);
	      
	  ////////////////////////////////////////////////////////////////////////
	    
	  String url = "jdbc:mysql://localhost/javabook";
	  String user = "scott";
	  String password = "tiger";

	  Connection myConn = DriverManager.getConnection(url, user, password);
	   
      String sql2 = "UPDATE Staff SET id = ?, lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = '" + tfID.getText() + "';";
      PreparedStatement myStmt2 = myConn.prepareStatement(sql2);
      myStmt2.setString(1, tfID.getText());
      myStmt2.setString(2, tfLastName.getText());
      myStmt2.setString(3, tfFirstName.getText());
      myStmt2.setString(4, tfMiddleInitial.getText());
      myStmt2.setString(5, tfStreet.getText());
      myStmt2.setString(6, tfCity.getText());
      myStmt2.setString(7, tfState.getText());
      myStmt2.setString(8, tfTelephone.getText());
      myStmt2.setString(9, tfEmail.getText());

      myStmt2.executeUpdate();
      System.out.println("Update complete");
      myConn.close();
      
	    }
	    catch (IOException ex) {
	      ex.printStackTrace();
	    }
	  }

  /** Read a record at the specified position */
  public void readAddress(long position) throws IOException {
    raf.seek(position);
    String firstName = FixedLengthStringIO.readFixedLengthString(
      FIRST_NAME_SIZE, raf);
    String middleInitial = FixedLengthStringIO.readFixedLengthString(
    	      MIDDLE_INITIAL_SIZE, raf);
    String lastName = FixedLengthStringIO.readFixedLengthString(
    	      LAST_NAME_SIZE, raf);
    String street = FixedLengthStringIO.readFixedLengthString(
      STREET_SIZE, raf);
    String city = FixedLengthStringIO.readFixedLengthString(
      CITY_SIZE, raf);
    String state = FixedLengthStringIO.readFixedLengthString(
      STATE_SIZE, raf);
    String email = FixedLengthStringIO.readFixedLengthString(
      ZIP_SIZE, raf);
    String telephone = FixedLengthStringIO.readFixedLengthString(
    	      TELEPHONE_SIZE, raf);
    String id = FixedLengthStringIO.readFixedLengthString(
    	      ID_SIZE, raf);

    tfFirstName.setText(firstName);
    tfMiddleInitial.setText(middleInitial);
    tfLastName.setText(lastName);
    tfStreet.setText(street);
    tfCity.setText(city);
    tfState.setText(state);
    tfEmail.setText(email);
    tfTelephone.setText(telephone);
    tfID.setText(id);
  }


  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

class FixedLengthStringIO {
  /** Read fixed number of characters from a DataInput stream */
  public static String readFixedLengthString(int size,
      DataInput in) throws IOException {
    // Declare an array of characters
    char[] chars = new char[size];

    // Read fixed number of characters to the array
    for (int i = 0; i < size; i++)
      chars[i] = in.readChar();

    return new String(chars);
  }

  /** Write fixed number of characters to a DataOutput stream */
  public static void writeFixedLengthString(String s, int size,
      DataOutput out) throws IOException {
    char[] chars = new char[size];

    // Fill in string with characters
    s.getChars(0, s.length(), chars, 0);

    // Fill in blank characters in the rest of the array
    for (int i = Math.min(s.length(), size); i < chars.length; i++)
      chars[i] = ' ';

    // Create and write a new string padded with blank characters
    out.writeChars(new String(chars));
  }
}