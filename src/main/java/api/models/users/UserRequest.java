package api.models.users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {

    int id;
    String name;
    String email;

}
