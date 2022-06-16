package click.pranjalonline.bakibohibackend.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthExceptions extends RuntimeException{
    private HttpStatus status;
    private String message;
}
