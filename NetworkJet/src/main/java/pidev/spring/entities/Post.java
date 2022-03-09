package pidev.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_post;
	private int views;
	private int likes;
	private int dislikes;
	private String content;
	private String caption;
	@CreationTimestamp
	private Date creation_date;
	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private Set<Comment> comments;
	
	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private Set<Reaction> reacts;
	
	@ManyToOne
	@JsonIgnore
	private User user;
}
