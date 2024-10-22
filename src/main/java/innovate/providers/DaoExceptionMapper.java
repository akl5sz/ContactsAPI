package innovate.providers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import innovate.dao.DaoException;

@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException>{

	@Override
	public Response toResponse(DaoException exception) {
		//this function is going to be used automatically to 
		//create a response whenever there is a DaoException being thrown
		Map<String, String> error = new HashMap<>();
		error.put("message", exception.getMessage());
		error.put("when", new Date().toString());
		return Response.status(500).entity(error).build(); //returns a custom json with a custom error number of 500
	}
}