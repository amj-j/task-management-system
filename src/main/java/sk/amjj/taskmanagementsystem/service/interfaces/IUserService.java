package sk.amjj.taskmanagementsystem.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import sk.amjj.taskmanagementsystem.dto.user.UserLoginDto;
import sk.amjj.taskmanagementsystem.dto.user.UserRegistrationDto;
import sk.amjj.taskmanagementsystem.dto.user.UserUpdateDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.PasswordsDoNotMatchException;
import sk.amjj.taskmanagementsystem.exceptions.UsernameTakenException;
import sk.amjj.taskmanagementsystem.model.entities.User;

@Service
public interface IUserService {

    User authenticate(UserLoginDto loginInfo);
    
    List<User> getAll();

    User create(UserRegistrationDto req)  throws UsernameTakenException, PasswordsDoNotMatchException;

    User getById(long id) throws NotFoundException;

    User update(long id, UserUpdateDto req) throws NotFoundException;

    boolean verifyPassword(long id, String password) throws NotFoundException;

    User updatePassword(long id, String password, String confirmPassword) throws NotFoundException, PasswordsDoNotMatchException;

    void delete(long id) throws NotFoundException;

}
