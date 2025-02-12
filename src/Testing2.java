//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class Testing2 {
//
//	public String retrieveSTRData(String s, int i) {
//		String data = "";
//		try {
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
//			PreparedStatement ps = con.prepareStatement("select " + s + " from Inventory where BALLID = ?");
//			
//			ps.setInt(1, i);
//			
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				data = rs.getString(s);
//			}
//		}
//		catch(SQLException e) {
//			System.out.println("Error while connecting to the database");
//		}
//		return data;
//	}
//	
//	public int retrieveINTData(String s, int i) {
//		int data = 0;
//		try {
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
//			PreparedStatement ps = con.prepareStatement("select " + s + " from Inventory where BALLID = ?");
//			
//			ps.setInt(1, i);
//			
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				data = Integer.parseInt(rs.getString(s));
//			}
//		}
//		catch(SQLException e) {
//			System.out.println("Error while connecting to the database");
//		}
//		return data;
//	}
//	
//	public static void main(String[] args) {
//		Testing2 ins = new Testing2();
//		
//		String[][] test = new String[10][6];
//		int b = 0
//		for(int i = 0; i < methods.getMaxID(); i++) {
//			int a = b + 1;
//			while(!methods.isInt(methods.retrieveSTRData("BALLID", a))) {
//				a++;
//			}
//			data[i][0] = methods.retrieveSTRData("BALLID", a);
//			data[i][1] = methods.retrieveSTRData("BRAND", a);
//			data[i][2] = methods.retrieveSTRData("MODEL", a);
//			data[i][3] = methods.retrieveSTRData("GRADE", a);
//			data[i][4] = methods.retrieveSTRData("COLOR", a);
//			data[i][5] = methods.retrieveSTRData("QTY", a);
//			b = a;
//			
//		}
//		
//		for(int i = 0; i < 10; i++) {
//			if(!(ins.retrieveINTData("BALLID", i) == 0)) {
//				for(int a = 0; a < 6; a++) {
//					System.out.print(test[i][a]);
//					System.out.print("          ");
//				}
//			}
//			System.out.println();
//		}
//		
//	}
//
//}
