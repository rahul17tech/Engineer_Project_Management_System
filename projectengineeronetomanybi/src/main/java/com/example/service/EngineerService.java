package com.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.EngineerDTO;
import com.example.entity.Engineer;
import com.example.entity.Project;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EngineerRepository;
import com.example.repository.ProjectRepository;

@Service
public class EngineerService {

	@Autowired
	private EngineerRepository engineerRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ModelMapper modelMapper;

	public EngineerDTO saveEngineer(EngineerDTO e) {
		Engineer _e = toEngineer(e);
		engineerRepository.save(_e);
		return toEngineerDTO(_e);
	}

	public EngineerDTO getEngineerById(Long id) {
		Engineer e = engineerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Engineer with id: " + id + " not present!"));
		return toEngineerDTO(e);
	}

	public EngineerDTO updateEngineer(Long id, Long pid, EngineerDTO e) {
		Engineer eng = engineerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Engineer with id: " + id + " not present!"));
		Engineer _e = modelMapper.map(e, Engineer.class);
		eng.setEngineerName(_e.getEngineerName());// updating engineer name
		if (pid != null) {
			Project _p = projectRepository.findById(pid)
					.orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " not present!"));
			eng.setProject(_p);
		}

		engineerRepository.save(eng);
		return toEngineerDTO(eng);
	}

	public Engineer toEngineer(EngineerDTO e) {
		return modelMapper.map(e, Engineer.class);
	}

	public EngineerDTO toEngineerDTO(Engineer e) {
		return modelMapper.map(e, EngineerDTO.class);
	}

}
