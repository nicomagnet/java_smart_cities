package webserver.actors;

public class Windmill extends Actor {
	
	int identifier;
	
	public Windmill(int identifier) {
		this.identifier=identifier;
	}
	
	public String getName() {
		return "Windmill"+this.identifier;
	}
	
	public String getDayAheadProduction() {
		StringBuffer reply = new StringBuffer();
		reply.append("<?xml version=\"1.0\"?><dayaheadproduction><actor>"+this.getName()+"</actor><unit>MW</unit><month>"+this.currentMonth+"</month><dayinmonth>"+this.currentDayInMonth+"</dayinmonth>");
		for(int i=0;i<24;i++) {
			reply.append("<value>"+Math.random()*100+"</value>");
		}
		reply.append("</dayaheadproduction>");
		return reply.toString();
	}
	
}
