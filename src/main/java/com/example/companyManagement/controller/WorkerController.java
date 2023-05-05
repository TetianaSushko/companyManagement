package com.example.companyManagement.controller;

import com.example.companyManagement.entity.Worker;
import com.example.companyManagement.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/worker")
public class WorkerController {
    private WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Worker>> getAll() {
        log.info("Request [GET] to: /worker/all");
        return ResponseEntity.ok().body(workerService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Integer id){
        log.info("Request [GET] to: /worker/{id} = " + id);
        return ResponseEntity.ok().body(workerService.findById(id));
    }
    @GetMapping("/workerById/{id}")
    public ResponseEntity<List<Worker>> findWorkerByProjectsId(@PathVariable Integer id){
        log.info("Request [GET] to: /worker/workerById/{id} = " + id);
        return ResponseEntity.ok().body(workerService.findWorkerByProjectsId(id));
    }

    @PostMapping
    public ResponseEntity<Worker> add(@RequestBody Worker worker){
        log.info("Request [POST] to: /worker/{worker} = " + worker);
        return ResponseEntity.ok().body(workerService.save(worker));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        log.info("Request [DELETE] to: /worker/{id} = " + id);
       workerService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<Worker> update(@RequestBody Worker worker){
        log.info("Request [PUT] to: /worker/{worker} = " + worker);
        return ResponseEntity.ok().body(workerService.update(worker));
    }
}