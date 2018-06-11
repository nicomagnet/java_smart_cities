package webserver;

import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class WebServer {
  //address of the RESTful application server
  static private final String BASE_URI = "http://localhost:4020/";
static private HttpServer server = null;

public static void main(String[] args) {
    try {
      //declare the package where web objects are (optional)
      ResourceConfig resourceConfig = new PackagesResourceConfig("webserver.resources"); 
      //create the server
      server = HttpServerFactory.create(BASE_URI,resourceConfig); 
      //start the server: web object are found automatically and published
      server.start(); 
      System.out.println("Webserver " + BASE_URI + " is running");
      System.out.println("Press Enter to stop the server. ");
      System.in.read();
      server.stop(0);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
