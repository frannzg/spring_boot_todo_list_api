package com.frannzg.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.frannzg.todo_list.models.Tasks;

@Repository
public interface ITaskRepository extends JpaRepository<Tasks, Long> {
    
}
