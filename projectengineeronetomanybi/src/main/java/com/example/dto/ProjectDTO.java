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

	@NotNull(message = "Invalid: Project name cannot be null!!")
	@NotBlank(message = "Invalid: Project name cannot be blank!!")
	@Size(min = 2, max = 30, message = "Invalid Project name: must be of 2 - 30 characters")
	private String projectName;
	private List<EngineerDTO> engineer;

}
