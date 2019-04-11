package EventPackage;
import java.util.Vector;

public class Profile {
	String name;
	String username;
	String password;
	Vector<Event> attendingEvents;
	Vector<Event> attendedEvents;
		
	
	public Profile() {
		this.name = "";
		this.username = "";
		this.password = "";
		this.attendingEvents = new Vector<Event>();
		this.attendedEvents = new Vector<Event>();
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Vector<Event> getAttendingEvents() {
		return attendingEvents;
	}
	public void setAttendingEvents(Vector<Event> attendingEvents) {
		this.attendingEvents = attendingEvents;
	}
	public Vector<Event> getAttendedEvents() {
		return attendedEvents;
	}
	public void setAttendedEvents(Vector<Event> attended_Events) {
		this.attendedEvents = attended_Events;
	}
	public void addAttendingEvent(Event toAdd) {
		this.attendingEvents.add(toAdd);
	}
	public void removeAttendingEvent(Event toRemove) {
		this.attendingEvents.remove(toRemove);
	}
	public void addAttendingEvents(Vector<Event> toAdd) {
		this.attendingEvents.addAll(toAdd);
	}
	public void removeAttendingEvents(Vector<Event> toRemove) {
		this.attendingEvents.removeAll(toRemove);
	}
	public boolean containsAttendingEvent(Event toCheck) {
		return this.attendingEvents.contains(toCheck);
	}
	public boolean containsAttendingEvents(Vector<Event> toCheck) {
		return this.attendingEvents.containsAll(toCheck);
	}
	public void addAttendedEvent(Event toAdd) {
		this.attendedEvents.add(toAdd);
	} 	
	public void removeAttendedEvent(Event toRemove) {
		this.attendedEvents.remove(toRemove);
	}
	public void addAttendedEvents(Vector<Event> toAdd) {
		this.attendedEvents.addAll(toAdd);
	}
	public void removeAttendedEvents(Vector<Event> toRemove) {
		this.attendedEvents.removeAll(toRemove);
	}
	public boolean containsAttendedEvent(Event toCheck) {
		return this.attendedEvents.contains(toCheck);
	}
	public boolean containsAttendedEvents(Vector<Event> toCheck) {
		return this.attendedEvents.containsAll(toCheck);
	}
	
	

	
}
