package sk.amjj.taskmanagementsystem.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sk.amjj.taskmanagementsystem.model.entities.TaskCategory;
import sk.amjj.taskmanagementsystem.model.entities.User;

@Repository
public interface ITaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    
    List<TaskCategory> findAllByOwner(User owner);

    @Query("SELECT DISTINCT tc FROM TaskCategory tc JOIN tc.tasks t WHERE t.owner = :user")
    List<TaskCategory> findAllWithTasksForUser(@Param("user") User user);

}