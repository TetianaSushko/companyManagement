package com.example.companyManagement.service;

import com.example.companyManagement.entity.Worker;
import com.example.companyManagement.repository.WorkerRepository;
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
class WorkerServiceTest {

    @InjectMocks
    WorkerService workerService;

    @Mock
    WorkerRepository workerRepository;

    @Test
    public void findAll() {
        Worker worker1 = new Worker("firstName1", "lastName1", "workerType1", "workerLevel1");
        Worker worker2 = new Worker("firstName2", "lastName2", "workerType2", "workerLevel2");

        List<Worker> workers = new ArrayList<>(Arrays.asList(worker1, worker2));
        when(workerRepository.findAll()).thenReturn(workers);

        assertThat(workerService.findAll().size()).isEqualTo(2);
        assertThat(workerService.findAll().get(0).getFirstName()).isEqualTo(worker1.getFirstName());
        assertThat(workerService.findAll().get(1).getFirstName()).isEqualTo(worker2.getFirstName());
    }

    @Test
    public void findById() {
        Integer workerId = 1;
        Worker worker = new Worker("firstName1", "lastName1", "workerType1", "workerLevel1");
        when(workerRepository.findById(workerId)).thenReturn(Optional.of(worker));
        assertThat(workerService.findById(workerId)).isEqualTo(worker);
    }

    @Test
    public void save() {
        Worker worker = new Worker("firstName1", "lastName1", "workerType1", "workerLevel1");
        Worker savedWorker = new Worker("firstName1", "lastName1", "workerType1", "workerLevel1");
        savedWorker.setId(1);

        when(workerService.save(any(Worker.class))).thenReturn(savedWorker);
        assertThat(workerService.save(worker)).isEqualTo(savedWorker);
    }

    @Test
    public void update() {
        Integer workerId = 1;
        Worker newWorker = new Worker("firstNameNew", "lastNameNew", "workerTypeNew", "workerLevelNew");
        newWorker.setId(workerId);
        Worker dbWorker = new Worker("firstName1", "lastName1", "workerType1", "workerLevel1");
        dbWorker.setId(workerId);

        when(workerRepository.findById(workerId)).thenReturn(Optional.of(dbWorker));
        when(workerService.save(any(Worker.class))).thenReturn(newWorker);
        assertThat(workerService.update(newWorker)).isEqualTo(dbWorker);
    }

    @Test
    public void delete() {
        Integer workerId = 1;
        workerService.deleteById(workerId);
        verify(workerRepository, times(1)).deleteById(1);
    }

    @Test
    public void findWorkerByProjectsId() {
        Integer projectId = 1;
        Worker worker1 = new Worker("firstName1", "lastName1", "workerType1", "workerLevel1");
        Worker worker2 = new Worker("firstName2", "lastName2", "workerType2", "workerLevel2");

        List<Worker> workers = new ArrayList<>(Arrays.asList(worker1, worker2));

        when(workerRepository.findWorkerByProjectsId(projectId)).thenReturn(workers);
        assertThat(workerService.findWorkerByProjectsId(projectId)).isEqualTo(workers);
        assertThat(workerService.findWorkerByProjectsId(projectId).size()).isEqualTo(2);
    }
}