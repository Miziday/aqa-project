package api.schemes.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientRequestSchema {
    private String id;
    private String fullName;
    private String phone;
    private String email;
}
