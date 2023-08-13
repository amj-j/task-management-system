package sk.amjj.taskmanagementsystem.dto.user;

import lombok.Data;
import sk.amjj.taskmanagementsystem.model.entities.User;

@Data
public class UserInfoDto {
        
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    public UserInfoDto(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

}
