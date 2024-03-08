package org.formation.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LibelleEcriture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name = "id_categorie", insertable = false, updatable = false)
	private Categorie categorie;

}
