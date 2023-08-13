package sk.amjj.taskmanagementsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sk.amjj.taskmanagementsystem.model.entities.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

}
