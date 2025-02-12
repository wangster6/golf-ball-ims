import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventory2 {
	int columns;
	Methods methods;
	ArrayList<Object> alInventory;
	String[] arrInventory;
	int[] arrBallID;
	String[][] dArrInventory;
	Connection con;
	
	public Inventory2() {
		columns = 6;
		methods = new Methods();
		alInventory = new ArrayList<Object>();
		arrInventory = null;
		arrBallID = retrieveID(methods.countRows());
		dArrInventory = new String[arrBallID.length][columns];
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
	
	public String[][] getDArrInventory(){		
		for(int i = 0; i < arrBallID.length; i++) {
			alInventory.add(retrieveStrArrData(arrBallID[i]));	
		}
		for(int i = 0; i < alInventory.size(); i++) {
			String[] s = (String[]) alInventory.get(i);
			dArrInventory[i][0] = s[0];
			dArrInventory[i][1] = s[1];
			dArrInventory[i][2] = s[2];
			dArrInventory[i][3] = s[3];
			dArrInventory[i][4] = s[4];
			dArrInventory[i][5] = s[5];
		}
		return dArrInventory;
	}
}
