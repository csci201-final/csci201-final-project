package EventPackage;
import java.util.Vector;

public class Event {
	private int eventID;
	private int hostID;
	private String date;
	private String beginTime;
	private String endTime;
	private String eventName;
	private String location;
	private String tags;
	private String affiliation;
	private String details;
	private Vector<Integer> attending;
	private Vector<Integer> interested;
	private Vector<Integer> notAttending;
	
	
	public Event(int eventID, int hostID, String date, String beginTime, String endTime, String eventName, String location,
				String tags, String affiliation, String details, Vector<Integer> attending, Vector<Integer> interested, Vector<Integer> notAttending) {
		this.eventID = eventID;
		this.hostID = hostID;
		this.date = date;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.location = location;
		this.tags = tags;
		this.affiliation = affiliation;
		this.attending = attending;
		this.interested = interested;
		this.notAttending = notAttending;
	}

	public int getHostID() {
		return hostID;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getName() {
		return eventName;
	}
	
	public String getDetails() {
		return details;
	}
	
	public String getBegin() {
		return beginTime;
	}
	
	public String getEnd() {
		return endTime;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getTags() {
		return tags;
	}
	
	public String getAffiliation() {
		return affiliation;
	}
	
	public Vector<Integer> getAttending() {
		return attending;
	}
	
	public int getNumAttending() {
		return attending.size();
	}
	
	public Vector<Integer> getInterested() {
		return interested;
	}
	
	public int getNumInterested() {
		return interested.size();
	}
	
	public Vector<Integer> getNotAttending() {
		return notAttending;
	}
	
	public int getNumNotInterested() {
		return notAttending.size();
	}
}
