package pidev.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reaction implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReaction;
	private String type;
	@JsonIgnore
	@ManyToOne
	private User user;
	@JsonIgnore
	@ManyToOne
	private Post post;
	@JsonIgnore
	@ManyToOne
	private Comment comments;
}