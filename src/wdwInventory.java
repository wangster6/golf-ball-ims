import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class wdwInventory {
	Inventory inv;
	Methods methods;
	
	int columns = 6;
	String[][] data;
	String[][] filteredData;
	
	private JFrame frame;
	private JTable table;
	private JLabel lblLogoIcon;
	private JPanel panelFilter;
	private JLabel lblBrand;
	private JTextField tfBrand;
	private JLabel lblBallID;
	private JTextField tfBallID;
	private JLabel lblModel;
	private JTextField tfModel;
	private JLabel lblGrade;
	private JTextField tfGrade;
	private JLabel lblColor;
	private JTextField tfColor;
	private JLabel lblQty;
	private JTextField tfQty;
	private JButton btnFilter;
	private JButton btnInsert;
	private JButton btnModify;
	private JButton btnDelete;

	public void filterBallIDData(String str) {
		filteredData = data;
		for(int i = 0; i < methods.countRows(); i++) {
			if(!filteredData[i][0].equals(str)) {
				
			}
		}
		
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wdwInventory window = new wdwInventory();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public wdwInventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inv = new Inventory();
		methods = new Methods();

		// Data to be displayed in the JTable
		data = inv.getDArrInventory();
		
		// Data after filtered
		filteredData = data;
				
		// Column Names
		String[] columnNames = { "BALLID", "BRAND", "MODEL", "GRADE", "COLOR", "QTY" };
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		
		lblLogoIcon = new JLabel("");
		Image imgLogo = new ImageIcon(this.getClass().getResource("/GBILogo.jpg")).getImage();
		Image imgLogoModified = imgLogo.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		lblLogoIcon.setIcon(new ImageIcon(imgLogoModified));
		
		panelFilter = new JPanel();
		
		btnFilter = new JButton("Filter");
		
		btnInsert = new JButton("Insert");
		
		btnModify = new JButton("Modify");
		
		btnDelete = new JButton("Delete");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(82)
							.addComponent(lblLogoIcon))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelFilter, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnInsert, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModify, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLogoIcon, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panelFilter, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFilter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInsert)
						.addComponent(btnDelete)
						.addComponent(btnModify))
					.addGap(239))
		);
		
		lblBrand = new JLabel("BRAND");
		lblBrand.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfBrand = new JTextField();
		tfBrand.setColumns(10);
		
		lblBallID = new JLabel("BALLID");
		lblBallID.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfBallID = new JTextField();
		tfBallID.setColumns(10);
		
		lblModel = new JLabel("MODEL");
		lblModel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfModel = new JTextField();
		tfModel.setColumns(10);
		
		lblGrade = new JLabel("GRADE");
		lblGrade.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfGrade = new JTextField();
		tfGrade.setColumns(10);
		
		lblColor = new JLabel("COLOR");
		lblColor.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfColor = new JTextField();
		tfColor.setColumns(10);
		
		lblQty = new JLabel("QUANTITY");
		lblQty.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfQty = new JTextField();
		tfQty.setColumns(10);
		GroupLayout gl_panelFilter = new GroupLayout(panelFilter);
		gl_panelFilter.setHorizontalGroup(
			gl_panelFilter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilter.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addComponent(lblBallID, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfBallID, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addComponent(lblBrand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfBrand, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addComponent(lblModel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfModel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addComponent(lblGrade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfGrade, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addComponent(lblColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfColor, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panelFilter.createSequentialGroup()
							.addComponent(lblQty, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfQty, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelFilter.setVerticalGroup(
			gl_panelFilter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFilter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(tfBallID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addGap(3)
							.addComponent(lblBallID)))
					.addGap(18)
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(tfBrand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addGap(3)
							.addComponent(lblBrand)))
					.addGap(18)
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(tfModel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addGap(3)
							.addComponent(lblModel)))
					.addGap(18)
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(tfGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addGap(3)
							.addComponent(lblGrade)))
					.addGap(18)
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(tfColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addGap(3)
							.addComponent(lblColor)))
					.addGap(18)
					.addGroup(gl_panelFilter.createParallelGroup(Alignment.LEADING)
						.addComponent(tfQty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelFilter.createSequentialGroup()
							.addGap(3)
							.addComponent(lblQty)))
					.addContainerGap())
		);
		panelFilter.setLayout(gl_panelFilter);
		
		table = new JTable(data, columnNames);
		
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
	}
}
