package innovate.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import innovate.entity.Contact;

@Provider
@Produces("text/csv")
public class ContactToCsvMarshaller implements MessageBodyWriter<Contact>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		//we return t/f whether or not an object has to be converted into a csv or not
		//what happens is that we check the data type(Class<?> type) given and compare 
		//it to the contact(MessageBodyWriter<Contact>)
		
		return type.isAssignableFrom(Contact.class); //this marshaller should be use only for an object for this class
	}

	@Override
	public long getSize(Contact t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1; //content-length http response header
	}

	@Override
	public void writeTo(Contact t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		//convert object content into csv, we write the date into the entityStream(OutputStream)
		String out = String.format("%d,%s,%s,%s,%s,%s,%s\n", t.getId(), t.getName(), t.getGender(), t.getEmail(), 
				t.getPhone(), t.getCity(), t.getCountry());
		entityStream.write("Id,Name,Gender,Email,Phone,City,Country\n".getBytes());
		entityStream.write(out.getBytes());
		
	}

}
