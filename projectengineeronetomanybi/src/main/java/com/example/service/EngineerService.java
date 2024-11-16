package com.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.EngineerDTO;
import com.example.entity.Engineer;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EngineerRepository;

@Service
public class EngineerService {

	@Autowired
	private EngineerRepository engineerRepository;

	@Autowired
	private ModelMapper modelMapper;

	public EngineerDTO saveEngineer(EngineerDTO e) {
		Engineer _e = modelMapper.map(e, Engineer.class);
		engineerRepository.save(_e);
		return modelMapper.map(_e, EngineerDTO.class);
	}

	public EngineerDTO getEngineerById(Long id) {
		Engineer e = engineerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Engineer with id: " + id + " not present!"));
		return modelMapper.map(e, EngineerDTO.class);
	}

	public EngineerDTO updateEngineer(Long id, EngineerDTO e) {
		Engineer eng = engineerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Engineer with id: " + id + " not present!"));
		Engineer _e = modelMapper.map(e, Engineer.class);
		eng.setEngineerName(_e.getEngineerName());// updating engineer name
		eng.setRole(_e.getRole());// updating engineer name

		engineerRepository.save(eng);
		return modelMapper.map(eng, EngineerDTO.class);
	}

}
