package com.frannzg.todo_list.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.frannzg.todo_list.models.Tasks;
import com.frannzg.todo_list.services.TaskServiceImpl;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping("/all")
    public List<Tasks> findAllTasks(){
        return taskServiceImpl.findAllTasks();
    }

    @GetMapping("/all/status")
    public List<Tasks> findAllTasksByStatus(@RequestParam String status){
        return taskServiceImpl.findTaskByStatus(status);
    }

    @GetMapping("/find")
    public Tasks findTaskById(@RequestParam Long id){
        return taskServiceImpl.findTaskById(id).orElse(null);
    }

    @PostMapping("/save")
    public Tasks saveTask(@RequestBody Tasks task){
        return taskServiceImpl.saveTask(task);
    }

    @PutMapping("/update/{id}")
    public Tasks updateTask(@PathVariable Long id, @RequestBody Tasks task){
        return taskServiceImpl.updateTask(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Long id){
        taskServiceImpl.deleteTask(id);
    }
}
