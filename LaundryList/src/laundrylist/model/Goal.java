package laundrylist.model;

public class Goal {
	String photoURL="";
	String mission="";
	String security="";
	String category = "";
	int duration = 0;
	
	public void setURL(String URL) {
		photoURL = URL;
	}
	
	public String getURL() {
		return photoURL;
	}
	
	public String getMission() {
		return mission;
	}
	public void setMission(String s) {
		mission = s;
	}
	
	public void setCategory(String s) {
		category = s;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setSecurity(String s) {
		security = s;
	}
	
	public String getSecurity() {
		return security;
	}
	
	public void setDuration(int d) {
		duration = d;
	}
	
	public int getDuration() {
		return duration;
	}
}