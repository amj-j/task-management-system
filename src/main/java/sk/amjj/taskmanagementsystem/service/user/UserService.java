package sk.amjj.taskmanagementsystem.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.amjj.taskmanagementsystem.dto.user.UserLoginDto;
import sk.amjj.taskmanagementsystem.dto.user.UserRegistrationDto;
import sk.amjj.taskmanagementsystem.dto.user.UserUpdateDto;
import sk.amjj.taskmanagementsystem.exceptions.IdNotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.PasswordsDoNotMatchException;
import sk.amjj.taskmanagementsystem.exceptions.UsernameTakenException;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.model.repository.IUserRepository;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;
import sk.amjj.taskmanagementsystem.utils.PasswordUtils;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private IUserRepository userRepository;
    
    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }
    
    @Override
    public User authenticate(UserLoginDto loginInfo) {
        User user = this.userRepository.findByUsername(loginInfo.getUsername());
        if (user == null || !PasswordUtils.verifyPassword(loginInfo.getPassword(), user.getPassword())) {
            return null;
        }
        else {
            return user;
        }
    }

    @Override
    public User create(UserRegistrationDto reg) throws UsernameTakenException, PasswordsDoNotMatchException {
        if (this.userRepository.findByUsername(reg.getUsername()) != null) {
            throw new UsernameTakenException();
        }
        if (!(reg.getPassword().equals(reg.getConfirmPassword()))) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User();
        String hashedPassword = PasswordUtils.hashPassword(reg.getPassword());
        user.setUsername(reg.getUsername());
        user.setPassword(hashedPassword);
        user.setFirstName(reg.getFirstName());
        user.setLastName(reg.getLastName());
        user.setEmail(reg.getEmail());
        return this.userRepository.save(user);
    }

    @Override
    public User getById(long id) throws IdNotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new IdNotFoundException("User not found!");
        }
    }

    @Override
    public User update(long id, UserUpdateDto reg) throws IdNotFoundException {
        User user = this.getById(id);
        if (reg.getUsername() != null) {
            user.setUsername(reg.getUsername());
        }
        if (reg.getFirstName() != null) {
            user.setFirstName(reg.getFirstName());
        }
        if (reg.getLastName() != null) {
            user.setLastName(reg.getLastName());
        }
        if (reg.getEmail() != null) {
            user.setEmail(reg.getEmail());
        }
        return this.userRepository.save(user);
    }

    @Override
    public boolean verifyPassword(long id, String password) throws IdNotFoundException {
        User user = this.getById(id);
        return PasswordUtils.verifyPassword(password, user.getPassword());
    }

    @Override
    public User updatePassword(long id, String password, String confirmPassword) throws IdNotFoundException, PasswordsDoNotMatchException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = this.getById(id);
        String hashedPassword = PasswordUtils.hashPassword(password);
        user.setPassword(hashedPassword);
        return this.userRepository.save(user);
    }

    @Override
    public void delete(long id) throws IdNotFoundException {
        this.userRepository.delete(this.getById(id));
    }

}
