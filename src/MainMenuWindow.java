import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuWindow implements ActionListener{
	private JFrame mainMenuFrame;
	private JLabel mainMenuQuestion;
	private JButton mainMenuAddBallButton;
	private JButton mainMenuRemoveBallButton;
	private JButton mainMenuShowInventoryButton;
	private JButton mainMenuEndProgramButton;
	private JPanel mainMenuPanel;
	
	Database database;
	GolfUI golfui;
	
	private final int hGap;
	private final int vGap;
	
	public MainMenuWindow() {
		database = new Database();
		golfui = new GolfUI();
		
		hGap = 10;
		vGap = 15;
		
		mainMenuFrame = new JFrame();
		
		mainMenuQuestion = new JLabel("What would you like to do?", SwingConstants.CENTER);
		
		mainMenuAddBallButton = new JButton("Add Ball");
		mainMenuAddBallButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenuFrame.dispose();
						new AddBallWindow();
					}
				}
			);
		mainMenuRemoveBallButton = new JButton("Remove Ball");
		mainMenuRemoveBallButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainMenuFrame.dispose();
						new RemoveBallWindow();
					}
				} 
			);
		mainMenuShowInventoryButton = new JButton("Show Inventory");
		mainMenuShowInventoryButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						golfui.addBall();
					}
				}
			);
		mainMenuEndProgramButton = new JButton("End Program");
		mainMenuEndProgramButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						golfui.addBall();
					}
				}
			);
		
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		mainMenuPanel.setLayout(new GridLayout(0, 1, hGap, vGap));
		mainMenuPanel.add(mainMenuQuestion);
		mainMenuPanel.add(mainMenuAddBallButton);
		mainMenuPanel.add(mainMenuRemoveBallButton);
		mainMenuPanel.add(mainMenuShowInventoryButton);
		mainMenuPanel.add(mainMenuEndProgramButton);
		
		mainMenuFrame.add(mainMenuPanel, BorderLayout.CENTER);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setTitle("Main Menu");
		mainMenuFrame.pack();
		mainMenuFrame.setSize(400, 400);
		mainMenuFrame.setLocationRelativeTo(null);
		mainMenuFrame.setVisible(true);
	}

	public static void main(String[] args) {
		new MainMenuWindow();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
