import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventory {
	int columns;
	Methods methods;
	ArrayList<Object> alInventory;
	String[] arrInventory;
	int[] arrBallID;
	int[] arrFilteredBallID;
	String[][] dArrInventory;
	Connection con;
	ArrayList<Object> alFilteredInventory;
	
	public Inventory() {
		columns = 6;
		methods = new Methods();
		alInventory = new ArrayList<Object>();
		arrInventory = null;
		arrBallID = retrieveID(methods.countRows());
		arrFilteredBallID = null;
		dArrInventory = new String[arrBallID.length][columns];
		alFilteredInventory = new ArrayList<Object>();
	}
	
	public String[] retrieveStrArrData(int i) {
		String[] ball = new String[columns];
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			PreparedStatement ps = con.prepareStatement("select * from Inventory where BALLID = ?");
			
			ps.setInt(1, i);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ball[0] = rs.getString("BALLID");
				ball[1] = rs.getString("BRAND");
				ball[2] = rs.getString("MODEL");
				ball[3] = rs.getString("GRADE");
				ball[4] = rs.getString("COLOR");
				ball[5] = rs.getString("QTY");
			}
		}catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ball;
	}
	
	public int[] retrieveID(int size) {
		int[] tempBallID = new int[size];
		int i = 0;
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			PreparedStatement ps = con.prepareStatement("select BALLID from Inventory");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tempBallID[i] = rs.getInt("BALLID");
				i++;
			}
		}catch(SQLException e) {
			System.out.println("Error while connecting to the database");		
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return tempBallID;
	}
	
	public void listToArr() {
		for(int i = 0; i < alInventory.size(); i++) {
			String[] s = (String[]) alInventory.get(i);
			dArrInventory[i][0] = s[0];
			dArrInventory[i][1] = s[1];
			dArrInventory[i][2] = s[2];
			dArrInventory[i][3] = s[3];
			dArrInventory[i][4] = s[4];
			dArrInventory[i][5] = s[5];
		}
	}
	
	public void populateArrBallID() {
		arrBallID = retrieveID(methods.countRows());
	}
	
	public void populateALInventory() {
		for(int i = 0; i < arrBallID.length; i++) {
			alInventory.add(retrieveStrArrData(arrBallID[i]));	
		}
	}
	
	public String[][] getDArrInventory(){	
		populateArrBallID();
		populateALInventory();
		listToArr();
		return dArrInventory;
	}
	
	public String[] retrieveFilteredStrArrData(int id, String brand, String model, String grade, String color, int qty) {
		String[] ball = new String[columns];
		String idStr = "BALLID = ?";
		String brandStr = "BRAND = ?";
		String modelStr = "MODEL = ?";
		String gradeStr = "GRADE = ?";
		String colorStr = "COLOR = ?";
		String qtyStr = "QTY = ?";
		
		if(String.valueOf(id).equals("")) {
			if(!brand.equals("") || !model.equals("") || !grade.equals("") || !color.equals("") || !String.valueOf(qty).equals("")) {
				idStr = "BALLID = ? and ";
			}
			idStr = "";
		}
		if(brand.equals("")) {
			if(!model.equals("") || !grade.equals("") || !color.equals("") || !String.valueOf(qty).equals("")) {
				brandStr = "BRAND = ? and ";
			}
			brandStr = "";
		}
		if(model.equals("")) {
			if(!grade.equals("") || !color.equals("") || !String.valueOf(qty).equals("")) {
				modelStr = "MODEL = ? and ";
			}
			modelStr = "";
		}
		if(grade.equals("")) {
			if(!color.equals("") || !String.valueOf(qty).equals("")) {
				gradeStr = "GRADE = ? and ";
			}
			gradeStr = "";
		}
		if(color.equals("")) {
			if(!String.valueOf(qty).equals("")) {
				colorStr = "COLOR = ? and ";
			}
			colorStr = "";
		}
		if(String.valueOf(qty).equals("")) {
			idStr = "";
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			PreparedStatement ps = con.prepareStatement("select * from Inventory where " + idStr + brandStr + modelStr + gradeStr + colorStr + qtyStr);
			
			ps.setInt(1, id);
			ps.setString(2, brand);
			ps.setString(3, model);
			ps.setString(4, grade);
			ps.setString(5, color);
			ps.setInt(6, qty);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ball[0] = rs.getString("BALLID");
				ball[1] = rs.getString("BRAND");
				ball[2] = rs.getString("MODEL");
				ball[3] = rs.getString("GRADE");
				ball[4] = rs.getString("COLOR");
				ball[5] = rs.getString("QTY");
			}
		}catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ball;
	}
	
	public void populateFilteredALInventory(int id, String brand, String model, String grade, String color, int qty) {
		for(int i = 0; i < arrBallID.length; i++) {
			alFilteredInventory.add(retrieveFilteredStrArrData(id, brand, model, grade, color, qty));	
		}
	}
	
	public String[][] getFilteredDArrInventory(int id, String brand, String model, String grade, String color, int qty){	
		populateFilteredALInventory(id, brand, model, grade, color, qty);
		listToArr();
		return dArrInventory;
	}
	
	public int[] retrieveFilteredID(int size) {
		int[] tempBallID = new int[size];
		int i = 0;
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			PreparedStatement ps = con.prepareStatement("select BALLID from Inventory");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tempBallID[i] = rs.getInt("BALLID");
				i++;
			}
		}catch(SQLException e) {
			System.out.println("Error while connecting to the database");		
		}finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return tempBallID;
	}
}
