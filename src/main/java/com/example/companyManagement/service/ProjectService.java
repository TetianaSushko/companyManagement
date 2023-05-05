package com.example.companyManagement.service;

import com.example.companyManagement.entity.Project;
import com.example.companyManagement.exeption.NotFoundException;
import com.example.companyManagement.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }
    public Project findById(Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project with id=" + id + " not found in DB"));
        return project;
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }
    public void deleteById(Integer id){
        projectRepository.deleteById(id);
    }

    public Project update(Project project) {
        Project oldProject = projectRepository.findById(project.getId()).orElseThrow(() -> new NotFoundException("There no such project in DB to do update"));
        oldProject.setProjectName(project.getProjectName());
        return projectRepository.save(oldProject);
    }

    public List<Project> findProjectByWorkersId(Integer id) {
        return projectRepository.findProjectByWorkersId(id);
    }
}
