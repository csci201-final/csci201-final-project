import java.util.Date;
import java.util.Vector;

public class Event {
	
	Date date;
	Time time;
	String name;
	String location; 
	Profile host;
	int stars;
	Vector<Profile> attendees;
	Vector<String> tags;
	
	
	public Event(Date date, Time time, String name, String location, Profile host, Vector<Profile> attendees, int star, Vector<String> tags) {
		super();
		this.date = date;
		this.time = time;
		this.name = name;
		this.location = location;
		this.host = host;
		this.attendees = attendees;
		this.stars = stars;
		this.tags = tags;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Profile getHost() {
		return host;
	}
	public void setHost(Profile host) {
		this.host = host;
	} 
	public Vector<Profile> getAttendees() {
		return attendees;
	}
	public void setAttendees(Vector<Profile> attendees) {
		this.attendees = attendees;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Vector<String> getTags() {
		return tags;
	}

	public void setTags(Vector<String> tags) {
		this.tags = tags;
	}
	
	public void addAttendee(Profile toAdd) {
		attendees.add(toAdd);
	}
	public void addAttendees(Vector<Profile> toAdd) {
		attendees.addAll(toAdd);
	}
	public void removeAttendee(Profile toRemove) {
		attendees.remove(toRemove);
	}
	public void removeAttendees(Vector<Profile> toRemove) {
		attendees.removeAll(toRemove);
	}
	public boolean containsAttendee(Profile toCheck) {
		return attendees.contains(toCheck);
	}
	public boolean containsAttendee(Vector<Profile> toCheck) {
		return attendees.containsAll(toCheck);
	}
	public void addTag(String toAdd) {
		tags.add(toAdd);
	}
	public void addTags(Vector<String> toAdd) {
		tags.addAll(toAdd);
	}
	public void removeTag(String toRemove) {
		tags.remove(toRemove);
	}
	public void removeTags(Vector<String> toRemove) {
		tags.removeAll(toRemove);
	}
	public boolean containsTag(String toCheck) {
		return tags.contains(toCheck);
	}
	public boolean containsTags(Vector<String> toCheck) {
		return tags.containsAll(toCheck);
	}
	
}
