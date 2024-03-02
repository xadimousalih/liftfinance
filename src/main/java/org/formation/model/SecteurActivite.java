package org.formation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class SecteurActivite {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	private String name;
	@ManyToOne
	private TypeEntreprise typeEntreprise;
}
