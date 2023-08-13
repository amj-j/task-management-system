package sk.amjj.taskmanagementsystem.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.amjj.taskmanagementsystem.model.entities.User;

@Data
@NoArgsConstructor
public class UserUpdateDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    public UserUpdateDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}
