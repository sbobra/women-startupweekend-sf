package laundrylist.model;

public class State {
	private static State instance;
	private String name = "";
	public boolean DEBUG = false;
	private Goal currentNewGoal = new Goal();
	private int id = 0;
	public static State getInstance() {
		if (instance == null) {
            instance = new State();
        }
        return instance;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public Goal getGoal() {
		return currentNewGoal;
	}
	
	public void setGoal(Goal g) {
		currentNewGoal = g;
	}
	
	public String getName() {
		return name;
	}
	
	public void setID(int i) {
		id = i;
	}
	
	public int getID() {
		return id;
	}
}
