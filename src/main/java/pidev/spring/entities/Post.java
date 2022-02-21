package pidev.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private Date creation_date;
	@OneToMany(mappedBy = "post")
	private Set<Comment> comments;
}
