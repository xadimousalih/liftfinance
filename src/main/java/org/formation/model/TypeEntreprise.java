package org.formation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TypeEntreprise {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String code;
	
	private String name;

	private String description;

}
