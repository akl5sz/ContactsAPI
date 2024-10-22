package innovate.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import innovate.entity.Contact;

@Provider
@Consumes("text/csv")
public class CsvToContactUnmarshaller implements MessageBodyReader<Contact> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		//this method determines if the data can or cannot be read for that particular type
		return type.isAssignableFrom(Contact.class); //jersey can use this for only Contact class
	}

	@Override
	public Contact readFrom(Class<Contact> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		//this method actually reads the data from the entityStream(InputStream) and turns it into a contact object
		
		//InputStream has functions to read on byte at a time; however, we want to read the entire line
		//so we use a BufferedReader
		BufferedReader in = new BufferedReader(new InputStreamReader(entityStream));
		String csv = in.readLine();
		String[] args = csv.split(",");
		Contact c = new Contact();
		try{
			c.setId(Integer.parseInt(args[0]));
		} catch (NumberFormatException e) {
			
		}
		c.setName(args[1]);
		c.setGender(args[2]);
		c.setEmail(args[3]);
		c.setPhone(args[4]);
		c.setCity(args[5]);
		c.setCountry(args[6]);
		
		return c;
	}

}
