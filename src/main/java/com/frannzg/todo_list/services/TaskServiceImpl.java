package com.frannzg.todo_list.services;

import java.util.List;
import java.util.Optional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import com.frannzg.todo_list.models.Tasks;
import com.frannzg.todo_list.repository.ITaskRepository;

@Service
public class TaskServiceImpl implements ITaskService{

    @Autowired
    private ITaskRepository iTaskRepository;

    // Este método retorna una lista de Tasks
    @Override
    public List<Tasks> findAllTasks() {
        List<Tasks> tasks = iTaskRepository.findAll();
        return tasks;
    }

    @Override
    public List<Tasks> findTaskByStatus(String status) {
        if(status == null || status.isEmpty()){
            throw new IllegalArgumentException("Status is empty");
        } else {
            List<Tasks> tasks = iTaskRepository.findByStatus(status);
            return tasks;
        }
    }

    // Este método retorna un Optional<Tasks> en lugar de un Tasks
    @Override
    public Optional<Tasks> findTaskById(Long id) {
        Optional<Tasks> optionalTask = iTaskRepository.findById(id);
        if(optionalTask.isPresent()){
            return optionalTask;
        } else {
            return Optional.empty();
        }
    }

    // Este método retorna un Tasks en lugar de un Optional<Tasks>
    @Override
    public Tasks saveTask(Tasks task) {
       if(task == null){
            throw new IllegalArgumentException("Task not found");
       } else {
            if(task.getTask() == null || task.getTask().isEmpty()){
                throw new IllegalArgumentException("Task is empty");
            } else if(task.getDescription() == null || task.getDescription().isEmpty()){
                throw new IllegalArgumentException("Description is empty");
            } else {
            return iTaskRepository.save(task);
            }
       }
    }

    // Este método actualiza un Tasks y retorna un Tasks en lugar de un Optional<Tasks>
    @Override
    public Tasks updateTask(Long id, Tasks task) {
        if(id == null || task == null){
            throw new IllegalArgumentException("Task not found");
        } else {
            Tasks oldTask = iTaskRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Task"));
            oldTask.setTask(task.getTask());
            oldTask.setDescription(task.getDescription());
            oldTask.setStatus(task.getStatus());
            return iTaskRepository.save(oldTask);
        }
    }

    @Override
    public void deleteTask(Long id) {
        if(id != null){
            Optional<Tasks> optionalTask = iTaskRepository.findById(id);
            if(optionalTask.isEmpty()){
                throw new IllegalArgumentException("Task not found");
            } else {
                iTaskRepository.deleteById(id);
            }            
        } else {
            throw new IllegalArgumentException("Task not found");
        }
    }
    
}
