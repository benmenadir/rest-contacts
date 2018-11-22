package org.tutot.contacts.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tutot.contacts.dao.ContactDao;
import org.tutot.contacts.entities.Contact;

@Singleton
@Path("/contacts")
public class ContactsServices {
	private static String CHARSET = "charset=UTF-8";
	
	@EJB ContactDao contactsDAO;
	
	@DELETE
	@Path("/init")
	@Produces({MediaType.TEXT_PLAIN})
	public String initTable(){
		String message = "Base de donn�es initialis�e";	
		String resourceFile = "resources/personnes.csv";	
		try {
			contactsDAO.initContactsTable(resourceFile);
		} catch (Exception e) {
			message = "ERREUR "+e;
		}
		return message;
	}
	
	@DELETE
	@Path("/id/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delContact(@PathParam("id") long id){
		contactsDAO.delete(id);
		return "contact supprimé";
	}
	
	@GET
	@Path("/nom/{name}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Contact> getContactsByName(@PathParam("name") String name){
		Response.status(Response.Status.OK).header(HttpHeaders.CONTENT_TYPE,CHARSET).build();
		return contactsDAO.getByNom(name);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contact> getAllContacts(){
		Response.status(Response.Status.OK).header(HttpHeaders.CONTENT_TYPE,CHARSET).build();
		return contactsDAO.getAll();
	}
	
	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Contact getContactId(@PathParam("id") long id){
		Contact c = contactsDAO.getById(id).clone();
		return c;
	}
	
	@POST
	@Path("/new")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String createContact(Contact contact){
		contactsDAO.create(contact);
		return "contact créé";
	}
	
	@PUT
	@Path("/new")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String createPutContact(Contact contact){
		return this.createContact(contact);
	}
	
	@POST
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public String updateContact(Contact contact){
		contactsDAO.update(contact);
		return "contact modifié";
	}
	
	@DELETE
	@Path("/del/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteContact(@PathParam("id") long id){
		contactsDAO.delete(id);
		return "contact supprimé";
	}
	
	
}
