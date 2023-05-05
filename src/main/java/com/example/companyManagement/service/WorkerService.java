package com.example.companyManagement.service;

import com.example.companyManagement.entity.Worker;
import com.example.companyManagement.exeption.NotFoundException;
import com.example.companyManagement.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> findAll(){
        return workerRepository.findAll();
    }
    public Worker findById(Integer id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new NotFoundException("Worker with id=" + id + " not found in DB"));
        return worker;
    }

    public Worker save(Worker worker) {
        return workerRepository.save(worker);
    }
    public void deleteById(Integer id){
        workerRepository.deleteById(id);
    }

    public Worker update(Worker worker) {
        Worker oldWorker = workerRepository.findById(worker.getId()).orElseThrow(() -> new NotFoundException("There no such worker in DB to do update"));
        oldWorker.setFirstName(worker.getFirstName());
        oldWorker.setLastName(worker.getLastName());
        oldWorker.setWorkerType(worker.getWorkerType());
        oldWorker.setWorkerLevel(worker.getWorkerLevel());
        return workerRepository.save(oldWorker);
    }

    public List<Worker> findWorkerByProjectsId(Integer id) {
        return workerRepository.findWorkerByProjectsId(id);
    }
}
