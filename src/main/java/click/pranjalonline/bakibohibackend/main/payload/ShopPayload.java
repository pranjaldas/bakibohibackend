package click.pranjalonline.bakibohibackend.main.payload;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
public class ShopPayload {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String coordinates;
    @NotEmpty
    private String encodedPhoto;
    private MultipartFile file;
}
