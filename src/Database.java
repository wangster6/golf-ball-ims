import java.sql.*;
import java.util.Scanner;

public class Database {
	Scanner in;
	Connection con = null;
	
	public Database() {
		in = new Scanner(System.in);
	}
	
	public void Input(String brand, String model, String grade, String color, int qty) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
//			System.out.println("Connected with the database successfully");
			
			PreparedStatement inputPreparedStatement = con.prepareStatement("insert into Inventory(BRAND,MODEL,GRADE,COLOR,QTY) values(?, ?, ?, ?, ?)");
			inputPreparedStatement.setString(1, brand);
			inputPreparedStatement.setString(2, model);
			inputPreparedStatement.setString(3, grade);
			inputPreparedStatement.setString(4, color);
			inputPreparedStatement.setInt(5, qty);
			
			inputPreparedStatement.executeUpdate();
			System.out.println("\n[" + grade + "] " + brand + " " + model + " " + "(" + color + ") has been added to the database");
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void Delete(String brand, String model, String grade, String color) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
//			System.out.println("Connected with the database successfully");
			
			PreparedStatement deletePreparedStatement = con.prepareStatement("delete from Inventory where brand = ? and model = ? and grade = ? and color = ?");
			deletePreparedStatement.setString(1, brand);
			deletePreparedStatement.setString(2, model);
			deletePreparedStatement.setString(3, grade);
			deletePreparedStatement.setString(4, color);

			
			deletePreparedStatement.executeUpdate();
			System.out.println("\n[" + grade + "] " + brand + " " + model + " " + "(" + color + ") has been deleted from the database");
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void Update(int qty, String brand, String model, String grade, String color) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			//			System.out.println("Connected w DB Update");
			
			PreparedStatement updatePreparedStatement = con.prepareStatement("update Inventory set QTY = ? where brand = ? and model = ? and grade = ? and color = ?");
			updatePreparedStatement.setInt(1, qty);
			updatePreparedStatement.setString(2, brand);
			updatePreparedStatement.setString(3, model);
			updatePreparedStatement.setString(4, grade);
			updatePreparedStatement.setString(5, color);
			
			updatePreparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public int retrieveQuantity(String brand, String model, String grade, String color) {
		int currentQuantity = 0;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			//			System.out.println("Connected with the database successfully");
			
			PreparedStatement retrieveQuantityPreparedStatement = con.prepareStatement("select QTY from Inventory where brand = ? and model = ? and grade = ? and color = ?");
			retrieveQuantityPreparedStatement.setString(1, brand);
			retrieveQuantityPreparedStatement.setString(2, model);
			retrieveQuantityPreparedStatement.setString(3, grade);
			retrieveQuantityPreparedStatement.setString(4, color);
			
			ResultSet resultSet = retrieveQuantityPreparedStatement.executeQuery();
			while(resultSet.next()) {
				String retrieveQTY = resultSet.getString("QTY");
				currentQuantity = Integer.valueOf(retrieveQTY);
			}
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return currentQuantity;
	}
	
	public int doesBallExist(String brand, String model, String grade, String color) {
		int doesBallExist = 0;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			//			System.out.println("Connected with the database successfully");
			
			PreparedStatement doesBallExistPreparedStatement = con.prepareStatement("select COUNT(1) from Inventory where brand = ? and model = ? and grade = ? and color = ?");
			doesBallExistPreparedStatement.setString(1, brand);
			doesBallExistPreparedStatement.setString(2, model);
			doesBallExistPreparedStatement.setString(3, grade);
			doesBallExistPreparedStatement.setString(4, color);
			
			ResultSet resultSet = doesBallExistPreparedStatement.executeQuery();
			while(resultSet.next()) {
				String retrieveQTY = resultSet.getString("COUNT(1)");
				if(Integer.valueOf(retrieveQTY) > 0) {
					doesBallExist = 1;
				}
			}
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return doesBallExist;
	}
	
	public void showTable() {
		System.out.println();
		System.out.printf("%30s", "BRAND");
		System.out.printf("%30s", "MODEL");
		System.out.printf("%30s", "GRADE");
		System.out.printf("%30s", "COLOR");
		System.out.printf("%30s", "QTY");
		System.out.println();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			//			System.out.println("Connected with the database successfully");
			
			PreparedStatement showTablePreparedStatement = con.prepareStatement("select * from Inventory");
			
			ResultSet showTableResultSet = showTablePreparedStatement.executeQuery();
			while(showTableResultSet.next()) {
				String brand = showTableResultSet.getString("BRAND");
				String model = showTableResultSet.getString("MODEL");
				String grade = showTableResultSet.getString("GRADE");
				String color = showTableResultSet.getString("COLOR");
				int quantity = showTableResultSet.getInt("QTY");
				
				System.out.printf("%30s", brand);
				System.out.printf("%30s", model);
				System.out.printf("%30s", grade);
				System.out.printf("%30s", color);
				System.out.printf("%30d", quantity);
				System.out.println();
			}
			showTableOptions();
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void showTableOptions() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("A) Sort\nB) Filter\nC) Go Back To Menu");
		System.out.print("> ");
		String tableAction = in.nextLine().toUpperCase();
		
		switch(tableAction) {
		case "A":
			System.out.println("\nWhat column would you like to sort by?");
			System.out.println("A) Brand\nB) Model\nC) Grade\nD) Color\nE) Quantity");
			System.out.print("> ");
			String sortBy = in.nextLine().toUpperCase();
			
			while(!(sortBy.equals("A") || sortBy.equals("B") || sortBy.equals("C") || sortBy.equals("D") || sortBy.equals("E"))) {
				System.out.println("\nError: You did not pick any of the choices! Please type either A, B, C, D, or E!\n");
				System.out.println("\nWhat column would you like to sort by?");
				System.out.println("A) Brand\nB) Model\nC) Grade\nD) Color\nE) Quantity");
				System.out.print("> ");
				sortBy = in.nextLine().toUpperCase();
			}
			
			System.out.println("\nWhat order would you like to sort in?");
			System.out.println("A) Ascending\nB) Descending");
			System.out.print("> ");
			String order = in.nextLine().toUpperCase();
			
			while(!(order.equals("A") || order.equals("B"))) {
				System.out.println("\nError: You did not pick any of the choices! Please type either A or B!\n");
				System.out.println("\nWhat order would you like to sort in?");
				System.out.println("A) Ascending\nB) Descending");
				System.out.print("> ");
				order = in.nextLine().toUpperCase();
			}
			
			switch(sortBy) {
			case "A":
				if(order.equals("A")) {
					sortTable("BRAND", "ASC");
				}
				else if(order.equals("B")) {
					sortTable("BRAND", "DESC");
				}
				break;
			
			case "B":
				if(order.equals("A")) {
					sortTable("MODEL", "ASC");
				}
				else if(order.equals("B")) {
					sortTable("MODEL", "DESC");
				}
				break;
				
			case "C":
				if(order.equals("A")) {
					sortTable("GRADE", "ASC");
				}
				else if(order.equals("B")) {
					sortTable("GRADE", "DESC");
				}
				break;
				
			case "D":
				if(order.equals("A")) {
					sortTable("COLOR", "ASC");
				}
				else if(order.equals("B")) {
					sortTable("COLOR", "DESC");
				}
				break;
				
			case "E":
				if(order.equals("A")) {
					sortTable("QTY", "ASC");
				}
				else if(order.equals("B")) {
					sortTable("QTY", "DESC");
				}
				break;
			
			default:
				System.out.println("ERROR in showTable()");
			}
			break;
			
		case "B":
			System.out.println("\nWhat column would you like to filter by?");
			System.out.println("A) Brand\nB) Model\nC) Grade\nD) Color\nE) Quantity");
			System.out.print("> ");
			String filterColumn = in.nextLine().toUpperCase();
			
			while(!(filterColumn.equals("A") || filterColumn.equals("B") || filterColumn.equals("C") || filterColumn.equals("D") || filterColumn.equals("E"))) {
				System.out.println("\nError: You did not pick any of the choices! Please type either A, B, C, D, or E!\n");
				System.out.println("What column would you like to filter by?");
				System.out.println("A) Brand\nB) Model\nC) Grade\nD) Color\nE) Quantity");
				System.out.print("> ");
				filterColumn = in.nextLine().toUpperCase();
			}
			
			String filterBy;
			switch(filterColumn) {
			case "A":
				System.out.println("\nWhat BRAND would you like to filter by?");
				System.out.print("> ");
				filterBy = in.nextLine().toUpperCase();
				
				filterTable("BRAND", filterBy);
				break;
				
			case "B":
				System.out.println("\nWhat MODEL would you like to filter by?");
				System.out.print("> ");
				filterBy = in.nextLine().toUpperCase();
				
				filterTable("MODEL", filterBy);
				break;
				
			case "C":
				System.out.println("\nWhat GRADE would you like to filter by?");
				System.out.print("> ");
				filterBy = in.nextLine().toUpperCase();
				
				filterTable("GRADE", filterBy);
				break;
				
			case "D":
				System.out.println("\nWhat COLOR would you like to filter by?");
				System.out.print("> ");
				filterBy = in.nextLine().toUpperCase();
				
				filterTable("COLOR", filterBy);
				break;
			
			case "E":
				System.out.println("\nWhat QTY would you like to filter by?");
				System.out.print("> ");
				filterBy = in.nextLine().toUpperCase();
				
				filterTable("QTY", filterBy);
				break;				
			}
			

			break;
			
		case "C":
			GolfUI golfUI = new GolfUI();
			System.out.println();
			golfUI.promptAction();
			break;
			
		default:
			System.out.println("\nError: You did not pick one of the options! Please type in either A, B, or C!");
		}
	}
	
	public void sortTable(String orderBy, String whatOrder) {
		System.out.println();
		System.out.printf("%30s", "BRAND");
		System.out.printf("%30s", "MODEL");
		System.out.printf("%30s", "GRADE");
		System.out.printf("%30s", "COLOR");
		System.out.printf("%30s", "QTY");
		System.out.println();
		try {
			Connection sortTableConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
//			System.out.println("Connected with the database successfully");
			
			switch(whatOrder) {
			
			case "ASC":
				PreparedStatement sortTableASCPreparedStatement = sortTableConnection.prepareStatement("select * from Inventory order by " + orderBy + " ASC");
				
				ResultSet sortTableASCResultSet = sortTableASCPreparedStatement.executeQuery();
				while(sortTableASCResultSet.next()) {
					String brand = sortTableASCResultSet.getString("BRAND");
					String model = sortTableASCResultSet.getString("MODEL");
					String grade = sortTableASCResultSet.getString("GRADE");
					String color = sortTableASCResultSet.getString("COLOR");
					int quantity = sortTableASCResultSet.getInt("QTY");
					
					System.out.printf("%30s", brand);
					System.out.printf("%30s", model);
					System.out.printf("%30s", grade);
					System.out.printf("%30s", color);
					System.out.printf("%30d", quantity);
					System.out.println();
				}
				showTableOptions();
				
			case "DESC":
				PreparedStatement sortTableDESCPreparedStatement = sortTableConnection.prepareStatement("select * from Inventory order by " + orderBy + " DESC");
				
				ResultSet sortTableDESCResultSet = sortTableDESCPreparedStatement.executeQuery();
				while(sortTableDESCResultSet.next()) {
					String brand = sortTableDESCResultSet.getString("BRAND");
					String model = sortTableDESCResultSet.getString("MODEL");
					String grade = sortTableDESCResultSet.getString("GRADE");
					String color = sortTableDESCResultSet.getString("COLOR");
					int quantity = sortTableDESCResultSet.getInt("QTY");
					
					System.out.printf("%30s", brand);
					System.out.printf("%30s", model);
					System.out.printf("%30s", grade);
					System.out.printf("%30s", color);
					System.out.printf("%30d", quantity);
					System.out.println();
				}
				showTableOptions();
			}
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}
	}
	
	public void filterTable(String filterColumn, String filterBy) {
		System.out.println();
		System.out.printf("%30s", "BRAND");
		System.out.printf("%30s", "MODEL");
		System.out.printf("%30s", "GRADE");
		System.out.printf("%30s", "COLOR");
		System.out.printf("%30s", "QTY");
		System.out.println();
		try {
			Connection filterTableConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			
			PreparedStatement filterTablePreparedStatement = filterTableConnection.prepareStatement("select * from Inventory where " + filterColumn + " = ?");
			filterTablePreparedStatement.setString(1, filterBy);
			
			ResultSet filterTableResultSet = filterTablePreparedStatement.executeQuery();
			while(filterTableResultSet.next()) {
				String brand = filterTableResultSet.getString("BRAND");
				String model = filterTableResultSet.getString("MODEL");
				String grade = filterTableResultSet.getString("GRADE");
				String color = filterTableResultSet.getString("COLOR");
				int quantity = filterTableResultSet.getInt("QTY");
				
				System.out.printf("%30s", brand);
				System.out.printf("%30s", model);
				System.out.printf("%30s", grade);
				System.out.printf("%30s", color);
				System.out.printf("%30d", quantity);
				System.out.println();
			}
			showTableOptions();
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}
	}
}
