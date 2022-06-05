package click.pranjalonline.bakibohibackend.authentication.payload;


import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
