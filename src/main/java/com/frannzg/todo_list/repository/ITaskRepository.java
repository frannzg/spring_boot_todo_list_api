package com.frannzg.todo_list.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.frannzg.todo_list.models.Tasks;

@Repository
public interface ITaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByStatus(String status);
}
