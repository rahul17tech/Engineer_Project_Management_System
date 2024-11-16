package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.ProjectDTO;
import com.example.entity.Engineer;
import com.example.entity.Project;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EngineerRepository;
import com.example.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private EngineerRepository engineerRepository;

	@Autowired
	private ModelMapper modelMapper;

	List<Project> lp = new ArrayList<>();

	public List<Project> getAll(int pageNumber, int pageSize, String sortBy) {

		Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

		Page<Project> pe = projectRepository.findAll(p);

		return pe.getContent();
	}

//	public List<Project> getAll() {
//		return projectRepository.findAll();
//	}

	public List<ProjectDTO> saveAllProject(List<ProjectDTO> p) {
		p.forEach(_p -> {
			Project _temp = toProject(_p);
			for (Engineer e : _temp.getEngineer()) {
				e.setProject(_temp);
			}
			lp.add(_temp);
		});
		projectRepository.saveAll(lp);
		return p;
	}

	public ProjectDTO saveProject(ProjectDTO p) {
		Project _p = toProject(p);
		for (Engineer e : _p.getEngineer()) {
			e.setProject(_p);
		}
		projectRepository.save(_p);
		return toProjectDTO(_p);
	}

	public ProjectDTO getProjectById(Long id) {
		Project p = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " not present!"));
		return toProjectDTO(p);
	}

	public String deleteProj(Long id) {
		Project p = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " not present!"));
		projectRepository.delete(p);
		;
		return "Deleted!";
	}

	@Transactional
	public ProjectDTO updateProject(Long id, Long eid, ProjectDTO p) {

		Project _p = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " not present!"));

		Project _temp = toProject(p);
		_p.setProjectName(_temp.getProjectName()); // updating the project name..
		_p.setProjectDescription(_temp.getProjectDescription()); // updating project description..

		if (eid != null) { // if eid passed, then remove that eid..
			Engineer _e = engineerRepository.findById(eid)
					.orElseThrow(() -> new ResourceNotFoundException("Engineer with id: " + id + " not present!"));
			if (_p.getEngineer().contains(_e)) {
				_e.setProject(null);
				_p.getEngineer().remove(_e);
			} else {
				_e.setProject(_p);
				_p.getEngineer().add(_e);
			}

		}

		return toProjectDTO(projectRepository.save(_p));

	}

	public Project toProject(ProjectDTO p) {
		return modelMapper.map(p, Project.class);
	}

	public ProjectDTO toProjectDTO(Project p) {
		return modelMapper.map(p, ProjectDTO.class);
	}

}
