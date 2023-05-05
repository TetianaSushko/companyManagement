package com.example.companyManagement.repository;

import com.example.companyManagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>, CrudRepository<Project, Integer> {
    List<Project> findProjectByWorkersId(Integer id);
}
