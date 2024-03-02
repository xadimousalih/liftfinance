package org.formation.model;

import java.util.Date;

import javax.persistence.Entity;
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
public class Entreprise {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String code;
	
	private String name;
		    
    private String adresse;
    
    private String region;
    
    private String pays;
    
    private String email;
    
    private String telephone;
    
    private String ninea;

    private String nomLocalisation;
    
    private int agedudirigent;
    
	private double chiffreAffaire;

    private double longitude;
    
    private double latitude;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date created, disabled;
	
	@ManyToOne
	private SecteurActivite secteurActivite;
}
