package pidev.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Invitation")
public class Invitation {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private Long idInvitation;
	private Date date_expiration;
	private String message;
	private String token;
	private String email;

}
