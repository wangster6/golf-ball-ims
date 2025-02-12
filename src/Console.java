
public class Console {
	Database database;
	
	public Console() {
		database = new Database();
	}
	
	public void runConsole() {
		database.Delete("test", "test", "test", "test");
	}
	
	public static void main(String[] args) {
		Console console = new Console();
		console.runConsole();
	}

}
