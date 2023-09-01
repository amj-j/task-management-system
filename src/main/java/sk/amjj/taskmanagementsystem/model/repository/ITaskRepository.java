package sk.amjj.taskmanagementsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import sk.amjj.taskmanagementsystem.model.entities.Task;
import sk.amjj.taskmanagementsystem.model.entities.User;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Task t WHERE t.owner = :owner")
    void deleteAllByOwner(@Param("owner") User owner);

}
