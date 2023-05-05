package com.example.companyManagement.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "workers")
@ToString
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "worker_type")
    private String workerType;
    @Column(name = "worker_level")
    private String workerLevel;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "worker_project",
            joinColumns = { @JoinColumn(name = "worker_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") })
    private Set<Project> projects= new HashSet<>();;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public String getWorkerLevel() {
        return workerLevel;
    }

    public void setWorkerLevel(String workerLevel) {
        this.workerLevel = workerLevel;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Worker( String firstName, String lastName, String workerType, String workerLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workerType = workerType;
        this.workerLevel = workerLevel;
    }

    public Worker() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) && Objects.equals(firstName, worker.firstName) && Objects.equals(lastName, worker.lastName) && Objects.equals(workerType, worker.workerType) && Objects.equals(workerLevel, worker.workerLevel) && Objects.equals(projects, worker.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, workerType, workerLevel, projects);
    }
}
