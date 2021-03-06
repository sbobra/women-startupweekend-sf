package laundrylist.model;

public class Goal {
	String photoURL="";
	String mission="";
	String security="";
	String category = "";
	int duration = 0;
	String daysLeft = "";
	String ownerId = "";
	String isComplete = "";
	String timestamp = "";
	String id = "";
	String isAd = "";
	
	public String getIsAd() {
		return isAd;
	}
	
	public void setIsAd(String s) {
		isAd = s;
	}
	
	public void setId(String id) {
		this.id = id;
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setIsComplete(String complete) {
		this.isComplete = complete;
	}
	
	public String getIsComplete() {
		return isComplete;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setDaysLeft(String daysLeft) {
		this.daysLeft = daysLeft;
	}
	
	public String getDaysLeft() {
		return daysLeft;
	}
	
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