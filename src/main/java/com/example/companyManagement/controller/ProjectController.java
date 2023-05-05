package com.example.companyManagement.controller;

import com.example.companyManagement.entity.Project;
import com.example.companyManagement.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAll() {
        log.info("Request [GET] to: /project/all");
        return ResponseEntity.ok().body(projectService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable Integer id){
        log.info("Request [GET] to: /project/{id} = " + id);
        return ResponseEntity.ok().body(projectService.findById(id));
    }
    @GetMapping("/projectById/{id}")
    public ResponseEntity<List<Project>>  findProjectByWorkersId(@PathVariable Integer id){
        log.info("Request [GET] to: /project/projectById/{id} = " + id);
        return ResponseEntity.ok().body(projectService.findProjectByWorkersId(id));
    }
    @PostMapping
    public ResponseEntity<Project> save(@RequestBody Project project){
        log.info("Request [POST] to: /project/{project} = " + project);
        return ResponseEntity.ok().body(projectService.save(project));
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        log.info("Request [DELETE] to: /project/{id} = " + id);
        projectService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Project> update(@RequestBody Project project){
        log.info("Request [PUT] to: /project/{project} = " + project);
        return ResponseEntity.ok().body(projectService.update(project));
    }
}
