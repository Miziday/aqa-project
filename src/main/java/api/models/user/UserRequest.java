package api.models.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {

    String id;
    String name;
    String email;

}
