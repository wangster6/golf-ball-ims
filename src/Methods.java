import java.sql.*;

public class Methods {
	Connection con = null;
	
	public Methods() {
//		try {
//		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
//		}
//		catch(SQLException e){
//			
//		}
	}
	
	// Retrieve string data from SQL database
	public String retrieveSTRData(String s, int i) {
		String data = "";
		try {
			PreparedStatement ps = con.prepareStatement("select " + s + " from Inventory where BALLID = ?");
			
			ps.setInt(1, i);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				data = rs.getString(s);
			}
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database - 0001");
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return data;
	}
	
	// Retrieve integer data from SQL database
	public int retrieveINTData(String s, int i) {
		int data = 0;
		try {
			PreparedStatement ps = con.prepareStatement("select " + s + " from Inventory where BALLID = ?");
			
			ps.setInt(1, i);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				data = Integer.parseInt(rs.getString(s));
			}
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database - 0002");
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return data;
	}
	
	// Check count of rows in SQL database
	public int countRows() {
		int iCount = 0;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			PreparedStatement ps = con.prepareStatement("select COUNT(BALLID) as CNT from Inventory");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				iCount = Integer.parseInt(rs.getString("CNT"));
			}
			con.close();
		}
		catch(SQLException e) {
			//System.out.println("Error while connecting to the database - 0003");
			 e.printStackTrace();
		}
		finally{
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return iCount;
	}
	
	// Get max ID from SQL database
		public int getMaxID() {
			int max = 0;
			try {
				PreparedStatement ps = con.prepareStatement("select MAX(BALLID) from Inventory");

				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					max = Integer.parseInt(rs.getString("MAX(BALLID)"));
				}
			}
			catch(SQLException e) {
				System.out.println("Error while connecting to the database - 0004");
			}
			finally{
				if(con!=null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return max;
		}
	
	// Validate if provided String can be parsed to double
	public boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
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
			
	// Validate if provided String can be parsed to integer
	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} 
		catch (NumberFormatException e) {
			return false;
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
	public static void main(String[] args) {
		Methods methods = new Methods();
		System.out.println(methods.countRows());
	}
}
