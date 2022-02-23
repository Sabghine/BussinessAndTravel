package pidev.spring.entities;


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
@Table(name= "Planning")
public class Planning {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private Long idPlanning;
	private int nb_jours;
	private String idVol;
}
