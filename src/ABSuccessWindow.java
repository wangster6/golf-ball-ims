import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.xdevapi.Schema.Validation;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class ABSuccessWindow implements ActionListener{
	GolfUI golfui;
	Validation validation;
	
	private JFrame ABSFrame;
	
	private JLabel ABSSuccessLBL;

	private JButton ABSAddBallBTN;
	private JButton ABSMainMenuBTN;

	private JPanel ABSPanel;

	private final int hGap;
	private final int vGap;
	
	public ABSuccessWindow(String successMSG) {
		golfui = new GolfUI();
		validation = new Validation();
		
		hGap = 10;
		vGap = 25;
		
		ABSFrame = new JFrame();
		
		ABSSuccessLBL = new JLabel(successMSG, SwingConstants.CENTER);

		ABSAddBallBTN = new JButton("Add Another Ball");
		ABSAddBallBTN.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ABSFrame.dispose();
						new AddBallWindow();
					}
				}
			);
		ABSMainMenuBTN = new JButton("Main Menu");
		ABSMainMenuBTN.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ABSFrame.dispose();
						new MainMenuWindow();
					}
				}
			);
		
		ABSPanel = new JPanel();
		ABSPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		ABSPanel.setLayout(new GridLayout(3, 1, hGap, vGap));
		ABSPanel.add(ABSSuccessLBL);
		ABSPanel.add(ABSAddBallBTN);
		ABSPanel.add(ABSMainMenuBTN);
		
		
		ABSFrame.add(ABSPanel, BorderLayout.CENTER);
		ABSFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ABSFrame.setTitle("Add Ball");
		ABSFrame.pack();
		ABSFrame.setSize(600, 250);
		ABSFrame.setLocationRelativeTo(null);
		ABSFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
