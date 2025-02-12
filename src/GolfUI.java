import java.util.Scanner;

public class GolfUI {
	Database database;
	Scanner in;
	
	public GolfUI() {
		database = new Database();
		in = new Scanner(System.in);
	}

	public static void main(String[] args) {
		GolfUI golfUI = new GolfUI();
		golfUI.runUI();
	}
	
	// Method to run entire UI
	public void runUI() {
		promptAction();
	}
	
	// Method to prompt user action
	public void promptAction() {
		System.out.println("What would you like to do?");
		System.out.println("A) Add Ball");
		System.out.println("B) Remove Ball");
		System.out.println("C) Show Inventory");
		System.out.println("D) End Program");
		System.out.println();
		System.out.print("> ");
		String action = in.nextLine().toUpperCase();
		
		while(!(action.equals("A") || action.equals("B") || action.equals("C") || action.equals("D"))) {
			System.out.println("\nError: You did not pick any of the choices! Please type either A, B, C, or D!\n");
			System.out.println("What would you like to do?");
			System.out.println("A) Add Ball");
			System.out.println("B) Remove Ball");
			System.out.println("C) Show Inventory");
			System.out.println("D) End Program");
			System.out.print("> ");
			action = in.nextLine().toUpperCase();
		}
		
		if(action.equals("A")) {
			addBall();
		}
		else if(action.equals("B")) {
			removeBall();
		}
		else if(action.equals("C")) {
			database.showTable();
		}
		else if(action.equals("D")) {
			System.out.println("Shutting down program.");
			System.exit(0);
		}
		else {
			System.out.println("\nError: You did not pick one of the choices. Please pick A, B, C, or D!");
			System.out.println();
			promptAction();
		}
	}
	
	// Method to add balls
	public void addBall() {
		System.out.println("\nWhat brand is the ball?");
		System.out.print("> ");
		String tempAddBrand = in.nextLine().toUpperCase();
		
		System.out.println("What model is the ball?");
		System.out.print("> ");
		String tempAddModel = in.nextLine().toUpperCase();
		
		System.out.println("What grade is the ball?");
		System.out.print("> ");
		String tempAddGrade = in.nextLine().toUpperCase();
		
		System.out.println("What color is the ball?");
		System.out.print("> ");
		String tempAddColor = in.nextLine().toUpperCase();
		
		System.out.println("How many do you want to add?");
		int tempAddQty = Integer.parseInt(in.nextLine());
		while(tempAddQty <= 0) {
			System.out.println("Error! Please enter a number greater than 0");
			System.out.println("How many do you want to add?");
			System.out.print("> ");
			tempAddQty = Integer.parseInt(in.nextLine());
		}
		addBall2(tempAddBrand, tempAddModel, tempAddGrade, tempAddColor, tempAddQty);
	}
	
	// Add ball method part 2. Separated to use in JFrame
	public void addBall2(String tempAddBrand, String tempAddModel, String tempAddGrade, String tempAddColor, int tempAddQty) {
		if(database.doesBallExist(tempAddBrand, tempAddModel, tempAddGrade, tempAddColor) == 0) {
				database.Input(tempAddBrand, tempAddModel, tempAddGrade, tempAddColor, 1);
				for(int i = 0; i < tempAddQty - 1; i++) {
					database.Update(database.retrieveQuantity(tempAddBrand, tempAddModel, tempAddGrade, tempAddColor) + 1, tempAddBrand, tempAddModel, tempAddGrade, tempAddColor);
				}
				if(tempAddQty > 1) {
					new ABSuccessWindow(tempAddQty + " " + "[" + tempAddGrade + "] " + tempAddBrand + " " + tempAddModel + " (" + tempAddColor + ") have been added to the inventory");
				}
				else {
					new ABSuccessWindow(tempAddQty + " " + "[" + tempAddGrade + "] " + tempAddBrand + " " + tempAddModel + " (" + tempAddColor + ") has been added to the inventory");
				}
		}
		else if(database.doesBallExist(tempAddBrand, tempAddModel, tempAddGrade, tempAddColor) > 0){
			for(int i = 0; i < tempAddQty; i++) {
				database.Update(database.retrieveQuantity(tempAddBrand, tempAddModel, tempAddGrade, tempAddColor) + 1, tempAddBrand, tempAddModel, tempAddGrade, tempAddColor);
			}
			if(tempAddQty > 1) {
				new ABSuccessWindow("\n" + tempAddQty + " " + "[" + tempAddGrade + "] " + tempAddBrand + " " + tempAddModel + " (" + tempAddColor + ") have been added to the inventory");
			}
			else {
				new ABSuccessWindow("\n" + tempAddQty + " " + "[" + tempAddGrade + "] " + tempAddBrand + " " + tempAddModel + " (" + tempAddColor + ") has been added to the inventory");
			}
		}
		System.out.println();
	}
	
	// Method to remove balls
	public void removeBall() {
		System.out.println("\nWhat brand is the ball?");
		System.out.print("> ");
		String tempRemoveBrand = in.nextLine().toUpperCase();
		
		System.out.println("What model is the ball?");
		System.out.print("> ");
		String tempRemoveModel = in.nextLine().toUpperCase();
		
		System.out.println("What grade is the ball?");
		System.out.print("> ");
		String tempRemoveGrade = in.nextLine().toUpperCase();
		
		System.out.println("What color is the ball?");
		System.out.print("> ");
		String tempRemoveColor = in.nextLine().toUpperCase();
		
		if(database.doesBallExist(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor) == 0) {
			System.out.println("That ball does not exist!");
		}
		
		System.out.println("How many do you want to remove?");
		System.out.print("> ");
		int tempRemoveQty = Integer.parseInt(in.nextLine());
		while(tempRemoveQty <= 0 || tempRemoveQty > database.retrieveQuantity(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor)) {
			if(tempRemoveQty <= 0 && tempRemoveQty > database.retrieveQuantity(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor)) {
				System.out.println("Error! Please enter a number greater than 0");
				System.out.println("Error! You cannot remove more balls than there is in the inventory!");
				System.out.println("How many do you want to remove?");
				tempRemoveQty = Integer.parseInt(in.nextLine());
			}
			else if(tempRemoveQty <= 0 && !(tempRemoveQty > database.retrieveQuantity(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor))) {
				System.out.println("Error! Please enter a number greater than 0");
				System.out.println("How many do you want to remove?");
				tempRemoveQty = Integer.parseInt(in.nextLine());
			}
			else if(!(tempRemoveQty <= 0) && tempRemoveQty > database.retrieveQuantity(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor)) {
				System.out.println("Error! You cannot remove more balls than there is in the inventory!");
				System.out.println("How many do you want to remove?");
				tempRemoveQty = Integer.parseInt(in.nextLine());
			}
		}
		removeBall2(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor, tempRemoveQty);
	}
	
	public void removeBall2(String tempRemoveBrand, String tempRemoveModel, String tempRemoveGrade, String tempRemoveColor, int tempRemoveQty) {
		for(int i = 0; i < tempRemoveQty; i++) {
			database.Update(database.retrieveQuantity(tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor) - 1, tempRemoveBrand, tempRemoveModel, tempRemoveGrade, tempRemoveColor);
		}
		if(tempRemoveQty > 1) {
			new RBSuccessWindow(tempRemoveQty + " " + "[" + tempRemoveGrade + "] " + tempRemoveBrand + " " + tempRemoveModel + " (" + tempRemoveColor + ") have been removed from the inventory");
		}
		else {
			new RBSuccessWindow(tempRemoveQty + " " + "[" + tempRemoveGrade + "] " + tempRemoveBrand + " " + tempRemoveModel + " (" + tempRemoveColor + ") has been removed from the inventory");
		}
		System.out.println();
	}

}
