package org.tutot.contacts.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.tutot.contacts.entities.Contact;
import org.tutot.contacts.service.ContactsServices;

@Startup
@Singleton
public class ContactDao {

	@PersistenceContext(unitName="contacts") private EntityManager em;	
	
	public ContactDao() {
		System.out.println(">>>> CONSTRUCTEUR ContactsJpaDao");
	}
	

	public void initContactsTable(String resourceFile) throws Exception{
		String line = null;
		BufferedReader reader =new BufferedReader(new InputStreamReader(ContactsServices.class.getClassLoader().getResourceAsStream("resources/personnes.csv")));
		Query query = em.createNativeQuery("TRUNCATE TABLE personnes");
		query.executeUpdate();
		while((line  = reader.readLine()) != null){
			String[] fields = line.split(",");
			em.persist(new Contact(fields[1], fields[2], fields[3]));
		}
		reader.close();
	}



	public List<Contact> getByNom(String name) {
		return em.createNamedQuery("Contact.getByNom",Contact.class).setParameter("nom", name+"%").getResultList();
	}
	

	public List<Contact> getAll(){
		return em.createQuery("SELECT c FROM Contact c ORDER BY c.nom",Contact.class).getResultList();
	}
	

	public Contact getById(long id){
		return em.find(Contact.class, id);
	}
	

	public Contact create(Contact contact){
		em.persist(contact);
		return contact;
	}

	public void update(Contact contact) {
		em.merge(contact);	
	}

	public void delete(long id) {
		Contact c = em.find(Contact.class, id);
		em.remove(c);
		
	}

}
