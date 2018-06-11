package simulator;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class WebClient {
	static private final String BASE_URI = "http://localhost:4020/";
	static private final WebResource webserver;
	
	static {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
	    webserver = client.resource(BASE_URI);
	}
	
	static public WebResource getResource(String pathToResource) {
		return webserver.path(pathToResource);
	}
	
	static public String requestText(String pathToResource) {
		WebResource webResource = getResource(pathToResource);
		System.out.println("\tsend "+BASE_URI+pathToResource);
		return webResource.accept(MediaType.TEXT_PLAIN).get(String.class);
	}
	
	static public String requestXML(String pathToResource) {
		WebResource webResource = getResource(pathToResource);
		System.out.println("\tsend "+BASE_URI+pathToResource);
		return webResource.accept(MediaType.TEXT_XML).get(String.class);
	}

}
