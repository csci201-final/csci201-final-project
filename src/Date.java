
public class Date {
	final int YEAR = 2019;
	
	enum WEEKDAY{
		MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	}
	enum MONTH{
		JANURARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);
		
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
	
	public Date() {
		weekday = null;
		month = null;
		day = null;
	}
	public Date(WEEKDAY weekday, MONTH month, DAY day) {
		this.weekday = weekday;
		this.month = month;
		this.day = day;
		
	}
	public int getYEAR() {
		return YEAR;
	}
	public String getWeekday() {
		return weekday.toString();
	}
	public String getMonth_String() {
		return month.toString();
	} 
	public int getMonth_Integer() {
		return month.getNumVal();
	}
	public String toWeekdayFormat() {
		String to_return = this.weekday.toString() + " the " + this.day + this.day.getSuffix();
		return to_return;
	}
	public String toNumericalFormat() {
		String to_return = this.month.getNumVal() + "/" + this.day.getNumVal() + "/" + YEAR;
		return to_return;
	}
}
