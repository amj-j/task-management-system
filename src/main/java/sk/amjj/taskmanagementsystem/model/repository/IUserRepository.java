package sk.amjj.taskmanagementsystem.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sk.amjj.taskmanagementsystem.model.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    
}
