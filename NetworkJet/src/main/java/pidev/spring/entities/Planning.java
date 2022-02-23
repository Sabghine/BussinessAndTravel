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
@Table(name= "Planning")
public class Planning {
	private long idPlanning;
	private int nb_jours;
	private String idVol;
}
