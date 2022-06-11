package click.pranjalonline.bakibohibackend.main.payload;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails<T> {
    private Date timestamp;
    private String status;
    private String message;
    private T details;
}
