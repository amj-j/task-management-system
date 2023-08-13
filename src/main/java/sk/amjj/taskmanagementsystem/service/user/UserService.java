package sk.amjj.taskmanagementsystem.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.amjj.taskmanagementsystem.dto.user.UserLoginDto;
import sk.amjj.taskmanagementsystem.dto.user.UserRegistrationDto;
import sk.amjj.taskmanagementsystem.dto.user.UserUpdateDto;
import sk.amjj.taskmanagementsystem.exceptions.NotFoundException;
import sk.amjj.taskmanagementsystem.exceptions.PasswordsDoNotMatchException;
import sk.amjj.taskmanagementsystem.exceptions.UsernameTakenException;
import sk.amjj.taskmanagementsystem.model.entities.User;
import sk.amjj.taskmanagementsystem.model.repository.IUserRepository;
import sk.amjj.taskmanagementsystem.service.interfaces.IUserService;

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
        if (user == null || !(user.getPassword().equals(loginInfo.getPassword()))) {
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
        user.setUsername(reg.getUsername());
        user.setPassword(reg.getPassword());
        user.setFirstName(reg.getFirstName());
        user.setLastName(reg.getLastName());
        user.setEmail(reg.getEmail());
        return this.userRepository.save(user);
    }

    @Override
    public User getById(long id) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else {
            throw new NotFoundException();
        }
    }

    @Override
    public User update(long id, UserUpdateDto reg) throws NotFoundException {
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
    public boolean verifyPassword(long id, String password) throws NotFoundException {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException();
        }
        return user.get().getPassword().equals(password);
    }

    @Override
    public User updatePassword(long id, String password, String confirmPassword) throws NotFoundException, PasswordsDoNotMatchException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        Optional<User> optUser = this.userRepository.findById(id);
        if (!optUser.isPresent()) {
            throw new NotFoundException();
        }
        User user = optUser.get();
        user.setPassword(password);
        return this.userRepository.save(user);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.userRepository.delete(this.getById(id));
    }

}
