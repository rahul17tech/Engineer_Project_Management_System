package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EngineerDTO {

	@NotNull(message = "Invalid: Engineer name cannot be null!!")
	@NotBlank(message = "Invalid: Engineer name cannot be blank!!")
	@Size(min = 3, max = 30, message = "Invalid Engineer name: must be of 3 - 30 characters")
	private String engineerName;

	@NotNull(message = "Invalid: Engineer role cannot be null!!")
	@NotBlank(message = "Invalid: Engineer role cannot be blank!!")
	@Size(min = 3, max = 30, message = "Invalid Engineer role: must be of 3 - 30 characters")
	private String role;

	@JsonIgnore
	private ProjectDTO project;

}
