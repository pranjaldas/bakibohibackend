package click.pranjalonline.bakibohibackend.authentication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    private String name;
    private String email;
    private String username;
    private long phone;
    private String password;
    private String userType;
}
