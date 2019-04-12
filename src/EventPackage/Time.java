package EventPackage;

public class Time {
	enum TIMEFRAME{
		am, pm;
	}
	
	private int hour;
	private int minute;
	private TIMEFRAME timeframe;
	
	public Time() {
		hour = (Integer) null;
		minute = (Integer) null;
		timeframe = null;
	}
	
	public Time(int hour, int minute) {
		if(hour > 12)
			timeframe = TIMEFRAME.pm;
		else
			timeframe = TIMEFRAME.am;
		
		while(minute > 60) {
			minute -=60;
		}
		while(hour > 12) {
			hour -= 12;
		}
	}
	
	public String hour_minute_timeframe_String() {
		String toReturn =  hour + ":" + minute + " " + timeframe;
		return toReturn; 
	}
}
