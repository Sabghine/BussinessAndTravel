package pidev.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
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
	private long idInvitation;
	private Date date_invitation;
	private Date date_expiration;
	private String message;
	private String token;

}
