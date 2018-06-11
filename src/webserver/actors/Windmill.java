package webserver.actors;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import webserver.util.*;
import static webserver.util.SQLiteConnector.getWindmillData;

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
                        double energy;
                        energy=100;
                    try {
                        energy=getWindmillData(this.identifier,this.currentMonth,this.currentDayInMonth+1,i);
                    } catch (SQLException ex) {
                        Logger.getLogger(Windmill.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        reply.append("<value>"+energy+"</value>");  

		}
		reply.append("</dayaheadproduction>");
		return reply.toString();
	}
	
}
