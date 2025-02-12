import java.awt.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Testing {

	public static String[] retrieveArray(String column) {
		ArrayList<String> al = new ArrayList<String>();
		
    	try {
			Connection testConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			
			Statement stat = testConnection.createStatement();
			String selectQuery = "select " + column + " from inventory";
			ResultSet rs = stat.executeQuery(selectQuery);
			
			
			while(rs.next()) {
				al.add(rs.getString(column));
			}
		}
		catch(SQLException e) {
			System.out.println("Error while connecting to the database");
		}
	    
	    Set<String> set = new HashSet<>(al);
	    al.clear();
	    al.addAll(set);
	    
	    String[] choices = new String[al.size()];
	    return al.toArray(choices);
    }
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("A Simple GUI");
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500, 500);
	    frame.setLocation(430, 100);

	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code

	    frame.add(panel);

	    JLabel lbl = new JLabel("Select one of the possible choices and click OK");
	    lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
	    //lbl.setVisible(true); // Not needed

	    panel.add(lbl);

	    
	    

	    
	    

	    final JComboBox<String> cb = new JComboBox<String>(retrieveArray("brand"));

	    cb.setMaximumSize(cb.getPreferredSize()); // added code
	    cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
	    //cb.setVisible(true); // Not needed
	    panel.add(cb);

	    JButton btn = new JButton("OK");
	    btn.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
	    panel.add(btn);

	    frame.setVisible(true); // added code
		
		

	}

}
