package ie.gmit.svp;

public class Runner {
	static Menu menu;

	public Runner() {

	}

	// Main method runs the menu method which starts the whole process
	public static void main(String[] args) {
		menu = new Menu();
		try {
			menu.show();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
