import java.util.Vector;

public class Profile {
	String name;
	String username;
	String password;
	Vector<Event> to_attend_Events;
	Vector<Event> attended_Events;
		
	
	public Profile() {
		this.name = "";
		this.username = "";
		this.password = "";
		this.attended_Events = new Vector<Event>();
		this.to_attend_Events = new Vector<Event>();
	}
}
