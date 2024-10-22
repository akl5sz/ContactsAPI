package innovate.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import innovate.entity.Contact;

@Provider
@Produces("text/csv")
public class ArrayListToCsvMarshaller implements MessageBodyWriter<ArrayList<Contact>> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return type.isAssignableFrom(ArrayList.class);
	}

	@Override
	public long getSize(ArrayList<Contact> t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(ArrayList<Contact> list, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		entityStream.write("Id,Name,Gender,Email,Phone,City,Country\n".getBytes());
		for(Contact t : list) {
			String out = String.format("%d,%s,%s,%s,%s,%s,%s\n", t.getId(), t.getName(), t.getGender(), t.getEmail(), 
					t.getPhone(), t.getCity(), t.getCountry());
			entityStream.write(out.getBytes());
		}
		
	}

}
