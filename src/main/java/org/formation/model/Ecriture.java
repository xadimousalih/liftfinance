package org.formation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Ecriture {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String numero;

	@ManyToOne
	private LibelleEcriture libelle;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date created, disabled;
	
	private Double montantEcriture, totalMensuel, totalAnnee, totalGlobal;
	
	@Enumerated(EnumType.STRING)
    private MoisEnum mois;
	
	@ManyToOne
	private Categorie categorie;
	
	@ManyToOne
	private Entreprise entreprise;
}
