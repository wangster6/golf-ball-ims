import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class AddBallWindow implements ActionListener{
	GolfUI golfui;
	Methods methods;
	
	private JFrame AddBallFrame;
	
	private JLabel AddBallBrandQuestion;
	private JLabel AddBallModelQuestion;
	private JLabel AddBallGradeQuestion;
	private JLabel AddBallColorQuestion;
	private JLabel AddBallQuantityQuestion;
	
	private JTextField AddBallBrandTextField;
	private JTextField AddBallModelTextField;
	private JTextField AddBallGradeTextField;
	private JTextField AddBallColorTextField;
	private JTextField AddBallQuantityTextField;


	private JButton AddBallAddBallButton;
	private JButton AddBallMainMenuButton;

	private JPanel AddBallPanel;

	private final int hGap;
	private final int vGap;
	
	public AddBallWindow() {
		golfui = new GolfUI();
		methods = new Methods();
		
		hGap = 10;
		vGap = 25;
		
		AddBallFrame = new JFrame();
		
		AddBallBrandQuestion = new JLabel("Brand", SwingConstants.CENTER);
		AddBallModelQuestion = new JLabel("Model", SwingConstants.CENTER);
		AddBallGradeQuestion = new JLabel("Grade", SwingConstants.CENTER);
		AddBallColorQuestion = new JLabel("Color", SwingConstants.CENTER);
		AddBallQuantityQuestion = new JLabel("Quantity", SwingConstants.CENTER);

		AddBallBrandTextField = new JTextField(10);
		AddBallModelTextField = new JTextField(10);
		AddBallGradeTextField = new JTextField(10);
		AddBallColorTextField = new JTextField(10);
		AddBallQuantityTextField = new JTextField(10);
		
		AddBallQuantityTextField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  if(!AddBallQuantityTextField.getText().equals("")) {
					  warn();
				  }
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(!AddBallQuantityTextField.getText().equals("")) {
					  warn();
				  }
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(!AddBallQuantityTextField.getText().equals("")) {
					  warn();
				  }
			  }
				    
			  public void warn() {
				  if(!methods.isInt(AddBallQuantityTextField.getText())){
					  JOptionPane.showMessageDialog(AddBallFrame, "Error: Quantity Must Be An Integer!", "ERROR", JOptionPane.ERROR_MESSAGE);
					  SwingUtilities.invokeLater(new Runnable()
					  {
					      public void run()
					      {
					    	  AddBallQuantityTextField.setText("");
					      }
					  });
			     }
			  }
			});
			
		AddBallAddBallButton = new JButton("Add Ball");
		AddBallAddBallButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddBallFrame.dispose();
						golfui.addBall2(AddBallBrandTextField.getText().toUpperCase(), AddBallModelTextField.getText().toUpperCase(), AddBallGradeTextField.getText().toUpperCase(), AddBallColorTextField.getText().toUpperCase(), Integer.valueOf(AddBallQuantityTextField.getText().toUpperCase()));
					}
				}
			);
		AddBallMainMenuButton = new JButton("Main Menu");
		AddBallMainMenuButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddBallFrame.dispose();
						new MainMenuWindow();
					}
				}
			);
		
		AddBallPanel = new JPanel();
		AddBallPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		AddBallPanel.setLayout(new GridLayout(6, 2, hGap, vGap));
		AddBallPanel.add(AddBallBrandQuestion);
		AddBallPanel.add(AddBallBrandTextField);
		AddBallPanel.add(AddBallModelQuestion);
		AddBallPanel.add(AddBallModelTextField);
		AddBallPanel.add(AddBallGradeQuestion);
		AddBallPanel.add(AddBallGradeTextField);
		AddBallPanel.add(AddBallColorQuestion);
		AddBallPanel.add(AddBallColorTextField);
		AddBallPanel.add(AddBallQuantityQuestion);
		AddBallPanel.add(AddBallQuantityTextField);
		AddBallPanel.add(AddBallAddBallButton);
		AddBallPanel.add(AddBallMainMenuButton);
		
		
		AddBallFrame.add(AddBallPanel, BorderLayout.CENTER);
		AddBallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AddBallFrame.setTitle("Add Ball");
		AddBallFrame.pack();
		AddBallFrame.setSize(400, 400);
		AddBallFrame.setLocationRelativeTo(null);
		AddBallFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
