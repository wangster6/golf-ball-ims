import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.xdevapi.Schema.Validation;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class RemoveBallWindow implements ActionListener{
	GolfUI golfui;
	Validation validation;
	
	private JFrame RBFrame;
	private JLabel RBBrandLBL, RBModelLBL, RBGradeLBL, RBColorLBL, RBQuantityLBL;
	private JComboBox<String> RBBrandCB, RBModelCB, RBGradeCB, RBColorCB, RBQuantityCB;
	private JButton RBRemoveBallBTN, RBMainMenuBTN;
	private JPanel RBPanel;
	
	private final int hGap;
	private final int vGap;
	
	public RemoveBallWindow() {
		golfui = new GolfUI();
		validation = new Validation();
		
		hGap = 10;
		vGap = 25;
		
		RBFrame = new JFrame();
		
		RBBrandLBL = new JLabel("Brand", SwingConstants.CENTER);
		RBModelLBL = new JLabel("Model", SwingConstants.CENTER);
		RBGradeLBL = new JLabel("Grade", SwingConstants.CENTER);
		RBColorLBL = new JLabel("Color", SwingConstants.CENTER);
		RBQuantityLBL = new JLabel("Quantity", SwingConstants.CENTER);
		
		RBBrandCB = new JComboBox<String>(retrieveArray("BRAND"));
		RBBrandCB.setSelectedIndex(-1);
		RBBrandCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(RBBrandCB)) {
					RBModelCB.removeAllItems();
					RBGradeCB.removeAllItems();
					RBColorCB.removeAllItems();
					RBQuantityCB.removeAllItems();
					
					String[] tempModel = retrieveArrayWhere("MODEL", RBBrandCB.getSelectedItem().toString(), "", "", "");
					String[] tempGrade = retrieveArrayWhere("GRADE", RBBrandCB.getSelectedItem().toString(), "", "", "");
					String[] tempColor = retrieveArrayWhere("COLOR", RBBrandCB.getSelectedItem().toString(), "", "", "");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), "", "", "");
					
					for(int i = 0; i < tempModel.length; i++) {
						RBModelCB.addItem(tempModel[i]);
					}
					for(int i = 0; i < tempGrade.length; i++) {
						RBGradeCB.addItem(tempGrade[i]);
					}
					
					for(int i = 0; i < tempColor.length; i++) {
						RBColorCB.addItem(tempColor[i]);
					}
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				else if(e.getSource().equals(RBModelCB)) {
					System.out.println("SUCCESS");
					RBGradeCB.removeAllItems();
					RBColorCB.removeAllItems();
					RBQuantityCB.removeAllItems();
					
					String[] tempGrade = retrieveArrayWhere("GRADE", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), "", "");
					String[] tempColor = retrieveArrayWhere("COLOR", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), "", "");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), "", "");
					
					for(int i = 0; i < tempGrade.length; i++) {
						RBGradeCB.addItem(tempGrade[i]);
					}
					
					for(int i = 0; i < tempColor.length; i++) {
						RBColorCB.addItem(tempColor[i]);
					}
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				else if(e.getSource().equals(RBGradeCB)) {
					RBColorCB.removeAllItems();
					RBQuantityCB.removeAllItems();

					String[] tempColor = retrieveArrayWhere("COLOR", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), "");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), "");

					for(int i = 0; i < tempColor.length; i++) {
						RBColorCB.addItem(tempColor[i]);
					}
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				else if(e.getSource().equals(RBColorCB)) {
					RBQuantityCB.removeAllItems();

					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), RBColorCB.getSelectedItem().toString());
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				
				
			}	
		});
		
		RBModelCB = new JComboBox<String>(retrieveArrayWhere("MODEL", "TITLEIST", "", "", ""));
		RBModelCB.setSelectedIndex(-1);
		RBModelCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(RBModelCB)) {
					System.out.println("SUCCESS");
					RBGradeCB.removeAllItems();
					RBColorCB.removeAllItems();
					RBQuantityCB.removeAllItems();
					
					String[] tempGrade = retrieveArrayWhere("GRADE", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), "", "");
					String[] tempColor = retrieveArrayWhere("COLOR", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), "", "");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), "", "");
					
					for(int i = 0; i < tempGrade.length; i++) {
						RBGradeCB.addItem(tempGrade[i]);
					}
					
					for(int i = 0; i < tempColor.length; i++) {
						RBColorCB.addItem(tempColor[i]);
					}
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				else if(e.getSource().equals(RBGradeCB)) {
					RBColorCB.removeAllItems();
					RBQuantityCB.removeAllItems();

					String[] tempColor = retrieveArrayWhere("COLOR", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), "");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), "");

					for(int i = 0; i < tempColor.length; i++) {
						RBColorCB.addItem(tempColor[i]);
					}
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				else if(e.getSource().equals(RBColorCB)) {
					RBQuantityCB.removeAllItems();

					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), RBColorCB.getSelectedItem().toString());
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				
				
			}	
		});
		
		RBGradeCB = new JComboBox<String>(retrieveArrayWhere("GRADE", "Titleist", "", "", ""));
		RBGradeCB.setSelectedIndex(-1);
		RBGradeCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(RBGradeCB)) {
					RBColorCB.removeAllItems();
					RBQuantityCB.removeAllItems();

					String[] tempColor = retrieveArrayWhere("COLOR", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), "");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), "");

					for(int i = 0; i < tempColor.length; i++) {
						RBColorCB.addItem(tempColor[i]);
					}
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				else if(e.getSource().equals(RBColorCB)) {
					RBQuantityCB.removeAllItems();

					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), RBColorCB.getSelectedItem().toString());
					
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				
				
			}	
		});
		
		RBColorCB = new JComboBox<String>(retrieveArrayWhere("COLOR", "Titleist", "", "", ""));
		RBColorCB.setSelectedIndex(-1);
		RBColorCB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource().equals(RBColorCB)) {
					RBQuantityCB.removeAllItems();
					System.out.println("TESTING123");
					String[] tempQuantity = retrieveArrayWhere("QUANTITY", RBBrandCB.getSelectedItem().toString(), RBModelCB.getSelectedItem().toString(), RBGradeCB.getSelectedItem().toString(), RBColorCB.getSelectedItem().toString());
							
					for(int i = 0; i < tempQuantity.length; i++) {
						RBQuantityCB.addItem(tempQuantity[i]);
					}
				}
				
				
			}	
		});
		
		RBQuantityCB = new JComboBox<String>(retrieveArray("QUANTITY"));
		RBQuantityCB.setSelectedIndex(-1);
		
		RBRemoveBallBTN = new JButton("Remove Ball");
		RBRemoveBallBTN.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String RBBrand = RBBrandCB.getSelectedItem().toString();
						String RBModel = RBModelCB.getSelectedItem().toString();
						String RBGrade = RBGradeCB.getSelectedItem().toString();
						String RBColor = RBColorCB.getSelectedItem().toString();
						String RBQuantity = RBQuantityCB.getSelectedItem().toString();
						RBFrame.dispose();
						golfui.removeBall2(RBBrand, RBModel, RBGrade, RBColor, Integer.parseInt(RBQuantity));
					}
		});
		RBMainMenuBTN = new JButton("Main Menu");
		RBMainMenuBTN.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RBFrame.dispose();
						new MainMenuWindow();
					}
				}
			);
		
		RBPanel = new JPanel();
		RBPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		RBPanel.setLayout(new GridLayout(6, 2, hGap, vGap));
		RBPanel.add(RBBrandLBL);
		RBPanel.add(RBBrandCB);
		RBPanel.add(RBModelLBL);
		RBPanel.add(RBModelCB);
		RBPanel.add(RBGradeLBL);
		RBPanel.add(RBGradeCB);
		RBPanel.add(RBColorLBL);
		RBPanel.add(RBColorCB);
		RBPanel.add(RBQuantityLBL);
		RBPanel.add(RBQuantityCB);
		RBPanel.add(RBRemoveBallBTN);
		RBPanel.add(RBMainMenuBTN);
		
		
		RBFrame.add(RBPanel, BorderLayout.CENTER);
		RBFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RBFrame.setTitle("Add Ball");
		RBFrame.pack();
		RBFrame.setSize(400, 400);
		RBFrame.setLocationRelativeTo(null);
		RBFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new RemoveBallWindow();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

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
	
	public static String[] retrieveArrayModel(String column, String brand) {
		ArrayList<String> al = new ArrayList<String>();
		
    	try {
			Connection testConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			
			Statement stat = testConnection.createStatement();
			
			int level = 0;
			String selectQuery = "select " + column + " from inventory where brand = '" + brand + "'";
		
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
	
	public static String[] retrieveArrayWhere(String column, String brand, String model, String grade, String color) {
		ArrayList<String> al = new ArrayList<String>();
		
    	try {
			Connection testConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			
			Statement stat = testConnection.createStatement();
			
			String selectQuery = "";
			int level = 0;
			if(!brand.equals("") && !model.equals("") && !grade.equals("") && !color.equals("")) {
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "' and model = '" + model + "' and grade = '" + grade + "' and color = '" + color + "'";
			}
			else if(!brand.equals("") && !model.equals("") && !grade.equals("") && color.equals("")) {
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "' and model = '" + model + "' and grade = '" + grade + "'";
			}
			else if(!brand.equals("") && !model.equals("") && grade.equals("") && color.equals("")) {
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "' and model = '" + model + "'";
			}
			else if(!brand.equals("") && model.equals("") && grade.equals("") && color.equals("")){
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "'";
			}

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
	
	public static ArrayList<String> retrieveArrayWhereAL(String column, String brand, String model, String grade, String color) {
		ArrayList<String> al = new ArrayList<String>();
		
    	try {
			Connection testConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/golfballinventory", "root", "9565889697Rw");
			
			Statement stat = testConnection.createStatement();
			
			String selectQuery = "";
			int level = 0;
			if(!brand.equals("") && !model.equals("") && !grade.equals("") && !color.equals("")) {
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "' and model = '" + model + "' and grade = '" + grade + "' and color = '" + color + "'";
			}
			else if(!brand.equals("") && !model.equals("") && !grade.equals("") && color.equals("")) {
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "' and model = '" + model + "' and grade = '" + grade + "'";
			}
			else if(!brand.equals("") && !model.equals("") && grade.equals("") && color.equals("")) {
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "' and model = '" + model + "'";
			}
			else if(!brand.equals("") && model.equals("") && grade.equals("") && color.equals("")){
				selectQuery = "select " + column + " from inventory where brand = '" + brand + "'";
			}

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
	    return al;
    }
	
	class ItemChangeListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	          Object item = event.getSource();
	          // do something with object
	       }
	    }       
	}
	public void concat(String[] first, String[] second) {
		String[] both = ArrayUtils.addAll(first, second);
	}
}
