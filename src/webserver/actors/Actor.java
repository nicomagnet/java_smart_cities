package webserver.actors;

public abstract class Actor {
	
	int currentMonth;
	int currentDayInMonth;

	public abstract String getName();
	
	public String setday(int monthNumber,int dayInMonth) {
		currentMonth=monthNumber;
		currentDayInMonth=dayInMonth;
		return this.getName() + ": day #" + dayInMonth + " of month #" + monthNumber;
	}
}
