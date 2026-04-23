package api.models.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private int id;
    private String email;
    private String name;

}
