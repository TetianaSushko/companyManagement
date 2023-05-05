package com.example.companyManagement.repository;

import com.example.companyManagement.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer>, CrudRepository<Worker, Integer> {
    List<Worker> findWorkerByProjectsId(Integer id);

}
