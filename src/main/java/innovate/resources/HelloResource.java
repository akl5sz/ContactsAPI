package innovate.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {

	// when we do a get request to /hello, this will be called
	@GET
	@Produces({"text/plain"})
	public String greet() {
		return "Hello, from Angie";
	}
	
	@GET
	@Produces({"application/xml"})
	public String greetAsXml() {
		return "<?xml version=\"1.0\" ?>\r\n"
				+ "<greeting>\r\n"
				+ "	<message>Hello, World</message>\r\n"
				+ "	<from>Angie</from>\r\n"
				+ "</greeting>\r\n"
				+ "";
	}
	
	@GET
	@Produces({"application/json"})
	public String greetAsJson() {
		return "{\r\n"
				+ "	“message” : “Hello World,\r\n"
				+ "	“from”: “Angie”\r\n"
				+ "}\r\n"
				+ "";
	}
}
