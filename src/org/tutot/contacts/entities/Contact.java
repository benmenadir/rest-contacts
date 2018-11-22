package org.tutot.contacts.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="personnes")
@NamedQueries({
	@NamedQuery(name="Contact.getByNom",query="SELECT c FROM Contact c WHERE LOWER(c.nom) LIKE :nom"),
	@NamedQuery(name="Contact.getByPrenom",query="SELECT c FROM Contact c WHERE c.prenom LIKE :prenom"),
	@NamedQuery(name="Contact.getByCivilite",query="SELECT c FROM Contact c WHERE c.civilite=:civilite")
})
public class Contact implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="pk")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String civilite;
	private String nom;
	private String prenom;
	
	
	public Contact() {}
	
	public Contact(String civilite, String nom, String prenom) {
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public Contact clone(){
		Contact c = new Contact();
		c.setCivilite(new String(civilite));
		c.setNom(new String(nom));
		c.setPrenom(new String(prenom));
		c.setId(id);
		return c;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
}
