package com.example.companyManagement.service;

import com.example.companyManagement.entity.Project;
import com.example.companyManagement.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectRepository projectRepository;

    @Test
    public void findAll() {
        Project project1 = new Project();
        project1.setId(1);
        project1.setProjectName("prj1");
        Project project2 = new Project();
        project2.setId(2);
        project2.setProjectName("prj2");

        List<Project> projects = new ArrayList<>(Arrays.asList(project1, project2));
        when(projectRepository.findAll()).thenReturn(projects);

        assertThat(projectService.findAll().size()).isEqualTo(2);
        assertThat(projectService.findAll().get(0).getProjectName()).isEqualTo(project1.getProjectName());
        assertThat(projectService.findAll().get(1).getProjectName()).isEqualTo(project2.getProjectName());
    }

    @Test
    public void findById() {
        Integer projectId = 1;
        Project project = new Project();
        project.setId(projectId);
        project.setProjectName("prj1");
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        assertThat(projectService.findById(projectId)).isEqualTo(project);
    }

    @Test
    public void save() {
        Project project = new Project();
        project.setProjectName("prj1");
        Project savedProject = new Project();
        savedProject.setId(1);
        savedProject.setProjectName("prj1");

        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);
        assertThat(projectService.save(project)).isEqualTo(savedProject);
    }


    @Test
    public void update() {
        Integer projectId = 1;
        Project newProject = new Project();
        newProject.setId(projectId);
        newProject.setProjectName("newProjectName");
        Project dbProject = new Project();
        dbProject.setId(projectId);
        dbProject.setProjectName("prj1");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(dbProject));
        when(projectService.save(any(Project.class))).thenReturn(newProject);
        assertThat(projectService.update(newProject)).isEqualTo(dbProject);
    }

    @Test
    public void delete() {
        Integer projectId = 1;
        projectService.deleteById(projectId);
        verify(projectRepository, times(1)).deleteById(1);
    }

    @Test
    public void findProjectByWorkersId() {
        Integer workerId = 1;
        Project project1 = new Project();
        project1.setId(1);
        project1.setProjectName("prj1");
        Project project2 = new Project();
        project2.setId(2);
        project2.setProjectName("prj2");

        List<Project> projects = new ArrayList<>(Arrays.asList(project1, project2));

        when(projectRepository.findProjectByWorkersId(workerId)).thenReturn(projects);
        assertThat(projectService.findProjectByWorkersId(workerId)).isEqualTo(projects);
        assertThat(projectService.findProjectByWorkersId(workerId).size()).isEqualTo(2);
    }
}