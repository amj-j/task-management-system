package sk.amjj.taskmanagementsystem.dto.user;

import lombok.Data;

@Data
public class UserRegistrationDto {
    
    private String username;

    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    private String email;

}