package com.frannzg.todo_list.services;

import java.util.List;
import java.util.Optional;
import com.frannzg.todo_list.models.Tasks;

public interface ITaskService {
    List<Tasks> findAllTasks();
    List<Tasks> findTaskByStatus(String status);
    Optional <Tasks> findTaskById(Long id);
    Tasks saveTask(Tasks task);
    Tasks updateTask(Long id, Tasks task);
    void deleteTask(Long id);
}
