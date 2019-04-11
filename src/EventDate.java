import java.util.Date;

public class EventDate {
	static Date today = new Date();
	final int YEAR = 2019;
	
	enum WEEKDAY{
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
	}
	enum MONTH{
		Janurary(1), February(2), March(3), April(4), May(5), June(6), July(7), August(8), September(9), October(10), November(11), December(12);
		
		private int numVal;
		MONTH(int numVal){
			this.numVal = numVal;
		}
		public int getNumVal() {
			return numVal;
		}
	}
	enum DAY{
		FIRST(1 ,"st"), SECOND(2, "nd"), THIRD(3, "rd"), FOURTH(4), FIFTH(5), SIXTH(6), SEVENTH(7), EIGHTH(8), NINTH(9), TENTH(10),
		ELEVENTH(11), TWELTH(12), THIRTEENTH(13), FOURTEENTH(14), FIFTEENTH(15), SIXTEENTH(16), SEVENTEENTH(17), EIGHTEENTH(18), NINETEENTH(19), TWENTIETH(20),
		TWENTYFIRST(21, "st"), TWENTYSECOND(22, "nd"), TWENTYTHIRD(23, "rd"), TWENTYFOURTH(24), TWENTYFIFTH(25), TWENTYSIXTH(26), TWENTYSEVENTH(27), TWENTYEIGHTH(28), TWENTYNINTH(29), THIRTIETH(30),
		THIRTYFIRST(31, "st");
		
		private int numVal;
		private String suffix;
		DAY(int numVal){
			this.numVal = numVal;
			suffix = "th";
		}
		DAY(int numVal, String suffix){
			this.numVal = numVal;
			this.suffix = suffix;
		}
		public int getNumVal() {
			return numVal;
		}
		public String getSuffix() {
			return suffix;
		}
	}
	
	WEEKDAY weekday; 
	MONTH month;
	DAY day;
	
	public EventDate() {
		weekday = null;
		month = null;
		day = null;
	}
	public EventDate(WEEKDAY weekday, MONTH month, DAY day) {
		this.weekday = weekday;
		this.month = month;
		this.day = day;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventDate other = (EventDate) obj;
		if (YEAR != other.YEAR)
			return false;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (weekday != other.weekday)
			return false;
		return true;
	}
	
	public boolean equals(Date d) {
		if(!d.toString().substring(0,3).equalsIgnoreCase(this.weekday.toString().substring(0,3)))
			return false;
		if(!d.toString().substring(4,7).equalsIgnoreCase(this.month.toString().substring(0,3)))
			return false;
		if((!d.toString().substring(8,10).equalsIgnoreCase("0" + this.day.numVal) && (!d.toString().equalsIgnoreCase("" + this.day.numVal))))
			return false;
		return true;
	}
	
	//--------------------GET YEAR--------------------
	public int getYEAR() {
		return YEAR;
	}
	
	//--------------------GET WEEKDAY--------------------
	public String getWeekday() {
		return weekday.toString();
	}
	
	//--------------------GET MONTH AS STRING--------------------
	public String getMonth_String() {
		return month.toString();
	} 
	
	//--------------------GET MONTH AS INTEGER--------------------
	public int getMonth_Integer() {
		return month.getNumVal();
	}

	//--------------------GET DATE w/ FORMAT MONTH+DAY+YEAR as INTEGER (1/1/2019)--------------------
	public String month_day_year_Numerical() {
		String to_return = this.month.getNumVal() + "/" + this.day.getNumVal() + "/" + YEAR;
		return to_return;
	}
	
	//--------------------GET DATE w/ FORMAT MONTH+DAY as INTEGER (1/1)--------------------
	public String month_day_Numerical() {
		String to_return = this.month.getNumVal() + "/" + this.day.getNumVal();
		return to_return;
	}
	
	//--------------------GET DATE w/ FORMAT MONTH+DAY as STRING (Janurary 1st)--------------------
	public String month_day_String() {
		String to_return = this.month.toString()+ " " + this.day.getNumVal() + this.day.getSuffix();
		return to_return;
	}
}
