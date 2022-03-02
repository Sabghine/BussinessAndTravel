package pidev.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	



	@OneToMany(cascade = CascadeType.ALL , mappedBy="user")
	private Set<Feedback> feedback;
	
	
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy="user")
	@JsonIgnore
	private Set<Complaint> complaints;
	
}
	