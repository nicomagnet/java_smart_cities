package simulator;

public class SimulationController {
	
	static int[] numberOfDaysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
	static String[] actors = {"W143","W237"}; // DO NOT FORGET TO ADD ALL THE ACTORS WHOSE CURRENT TIME HAS TO BE MODIFIED
	static int curentDayInMonth = 1;
	static int currentMonth = 1;
	
	public static void stepActors() {
		if(curentDayInMonth==numberOfDaysInMonth[currentMonth-1]) { //end of month
			if(currentMonth==12) { //end of year
				currentMonth=1;
				curentDayInMonth=1;
			} else { // inside the year
				currentMonth++;
				curentDayInMonth=1;
			}
		} else { // inside the month
			curentDayInMonth++;
		}
		for (String actor:actors) 
			System.out.println(simulator.WebClient.requestText(actor+"/setday/"+currentMonth+"/"+curentDayInMonth));
	}
	
	static public void main(String[] args) {
		int numberOfDays = 10;
		for(int i=0;i<numberOfDays;i++) {
			System.out.println("===== new day =====");
			stepActors();
			System.out.println(simulator.WebClient.requestText("W143/getdayaheadproduction"));
			System.out.println(simulator.WebClient.requestText("W237/getdayaheadproduction"));
		}
	}

}
