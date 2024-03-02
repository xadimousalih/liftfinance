package org.formation.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {

	private long id;

	@NotEmpty(message = "Reference should not be empty")
	private String reference;
	
	@NotEmpty(message = "Name should not be empty")
	private String name;

	private String description;

}
