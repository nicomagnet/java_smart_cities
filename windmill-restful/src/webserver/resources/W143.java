package webserver.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import webserver.actors.Windmill;

@Path("/W143")
public class W143 {
	
	static Windmill windmill = new Windmill(143);

	@GET
	 @Path("/setday/{monthNumber}/{dayInMonth}") 
	 public String setday(@PathParam("monthNumber") String monthNumber,@PathParam("dayInMonth") String dayInMonth) { 
		return windmill.setday(Integer.parseInt(monthNumber),Integer.parseInt(dayInMonth));
	 }
	
	@GET
	@Path("/getdayaheadproduction")
	public String getAheadProduction() {
		return windmill.getDayAheadProduction();
	}

}
