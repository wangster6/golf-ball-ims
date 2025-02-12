import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.xdevapi.Schema.Validation;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class RBSuccessWindow implements ActionListener{
	GolfUI golfui;
	Validation validation;
	
	private JFrame RBSFrame;
	
	private JLabel RBSSuccessLBL;

	private JButton RBSAddBallBTN;
	private JButton RBSMainMenuBTN;

	private JPanel RBSPanel;

	private final int hGap;
	private final int vGap;
	
	public RBSuccessWindow(String successMSG) {
		golfui = new GolfUI();
		validation = new Validation();
		
		hGap = 10;
		vGap = 25;
		
		RBSFrame = new JFrame();
		
		RBSSuccessLBL = new JLabel(successMSG, SwingConstants.CENTER);

		RBSAddBallBTN = new JButton("Remove Another Ball");
		RBSAddBallBTN.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RBSFrame.dispose();
						new RemoveBallWindow();
					}
				}
			);
		RBSMainMenuBTN = new JButton("Main Menu");
		RBSMainMenuBTN.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RBSFrame.dispose();
						new MainMenuWindow();
					}
				}
			);
		
		RBSPanel = new JPanel();
		RBSPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		RBSPanel.setLayout(new GridLayout(3, 1, hGap, vGap));
		RBSPanel.add(RBSSuccessLBL);
		RBSPanel.add(RBSAddBallBTN);
		RBSPanel.add(RBSMainMenuBTN);
		
		
		RBSFrame.add(RBSPanel, BorderLayout.CENTER);
		RBSFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RBSFrame.setTitle("Add Ball");
		RBSFrame.pack();
		RBSFrame.setSize(600, 250);
		RBSFrame.setLocationRelativeTo(null);
		RBSFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
