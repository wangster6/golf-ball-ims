import javax.swing.*;

public class InvDisplay {
	Inventory inv;
	Methods methods;
	
	// Frame
	JFrame frame;
	
	// Table
	JTable table;
	
	// Buttons
	JButton BTNAddBall;
	
	public InvDisplay() {
		inv = new Inventory();
		methods = new Methods();
		
		// Frame initialization
		frame = new JFrame();
		
		// Frame title
		frame.setTitle("Inventory");
		
		// Data to be displayed in the JTable
		String[][] data = inv.getDArrInventory();
		
		// Column Names
        String[] columnNames = { "BALLID", "BRAND", "MODEL", "GRADE", "COLOR", "QTY" };
 
        // Initializing the JTable
        table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
 
        // Creating button
//        BTNAddBall = new JButton("Add Ball");
//        BTNAddBall.addActionListener(new ActionListener() {
//        	
//        });
        
        // Adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
 
    // Driver  method
    public static void main(String[] args)
    {
        new InvDisplay();
    }
}
