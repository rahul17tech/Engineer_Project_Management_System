package com.example.dto;

import java.util.List;

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
public class ProjectDTO {

	@NotNull(message = "Invalid: Project name cannot be null!!") // not null but can be empty
	@NotBlank(message = "Invalid: Project name cannot be blank!!") // not null+not blank+not empty
	@Size(min = 2, max = 30, message = "Invalid Project name: must be of 2 - 30 characters") // size should not be less
																								// than or greater than
																								// the specified field
																								// size
	private String projectName;

	@NotNull(message = "Invalid: Project description cannot be null!!")
	@Size(min = 10, max = 100, message = "Invalid Project description: must be of 10 - 100 characters")
	private String projectDescription;
	private List<EngineerDTO> engineer;

}
