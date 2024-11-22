package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EngineerDTO;
import com.example.service.EngineerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/engineer")
@CrossOrigin(origins = "http://localhost:3000")
public class EngineerController {

	@Autowired
	private EngineerService engineerService;

	@PostMapping("/save")
	public ResponseEntity<EngineerDTO> saveEngineer(@RequestBody @Valid EngineerDTO e) {
		return new ResponseEntity<>(engineerService.saveEngineer(e), HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<EngineerDTO> getE(@RequestParam Long id) {
		return new ResponseEntity<>(engineerService.getEngineerById(id), HttpStatus.FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<EngineerDTO> updateE(@RequestParam Long id, @RequestBody @Valid EngineerDTO e) {
		return new ResponseEntity<>(engineerService.updateEngineer(id, e), HttpStatus.OK);
	}
}
