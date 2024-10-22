package innovate.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import innovate.dao.ContactsDAO;
import innovate.dao.DaoException;
import innovate.dao.DaoFactory;
import innovate.entity.Contact;

@Path("/contacts")
public class ContactsResource {
	private ContactsDAO dao;
	
	public ContactsResource() throws DaoException {
		dao = DaoFactory.getContactsDao();
	}
	
	@GET
	@Produces({"application/json", "text/csv"})
	public Response getAllContacts() throws DaoException {		
		return Response.ok(dao.findAll()).build();
	}
	
	@GET
	@Path("/{contact_id}")
	@Produces({"application/json", "text/csv"})
	public Response getOneContact(@PathParam("contact_id")Integer id) throws DaoException {
		return Response.ok(dao.findById(id)).build();
	}
	
	@POST
	@Produces({"application/json", "text/csv"}) //generates json and csv responses
	@Consumes({"application/json", "text/csv"}) //accepts json and csv requests
	public Response addNewContact(Contact contact) throws DaoException {
		contact = dao.addContact(contact);
		return Response.ok(contact).build();
	}
	
	@PUT
	@Path("/{contact_id}")
	@Produces({"application/json"}) 
	@Consumes({"application/json"}) 
	public Response updateContact(@PathParam("contact_id") Integer id, Contact contact) throws DaoException {
		contact.setId(id);
		contact = dao.updateContact(contact);
		return Response.ok(contact).build();
	}
	
	@DELETE
	@Path("/{contact_id}")
	@Produces({"application/json"})
	public Response deleteContact(@PathParam("contact_id") Integer id) throws DaoException{
		dao.deleteContact(id);
		return Response.ok().build();
	}
}
