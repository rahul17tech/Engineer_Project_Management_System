package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProjectDTO;
import com.example.entity.Project;
import com.example.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/getall")
	public ResponseEntity<List<Project>> getAllProjects(@RequestParam int pageNumber, @RequestParam int pageSize,
			@RequestParam String sortBy) {
		return new ResponseEntity<>(projectService.getAll(pageNumber, pageSize, sortBy), HttpStatus.FOUND);
	}

//	@GetMapping("/allemployees")
//	public List<Project> getAlll() {
//		return projectService.getAll();
//	}

	@PostMapping("/saveall")
	public ResponseEntity<List<ProjectDTO>> saveAllProjects(@RequestBody @Valid List<ProjectDTO> p) {
		return new ResponseEntity<>(projectService.saveAllProject(p), HttpStatus.CREATED);
	}

	@PostMapping("/save")
	public ResponseEntity<ProjectDTO> saveProject(@RequestBody @Valid ProjectDTO p) {
		return new ResponseEntity<>(projectService.saveProject(p), HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<ProjectDTO> getP(@RequestParam Long id) {
		return new ResponseEntity<>(projectService.getProjectById(id), HttpStatus.FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<ProjectDTO> updateP(@RequestParam Long id, @RequestParam(required = false) Long empid,
			@RequestBody @Valid ProjectDTO p) {
		return new ResponseEntity<>(projectService.updateProject(id, empid, p), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteP(@RequestParam Long id) {
		return new ResponseEntity<>(projectService.deleteProj(id), HttpStatus.OK);
	}
}
