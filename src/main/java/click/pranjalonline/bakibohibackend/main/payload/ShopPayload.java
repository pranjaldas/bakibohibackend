package click.pranjalonline.bakibohibackend.main.payload;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ShopPayload {
    private String name;
    private String address;
    private MultipartFile photo;
    private String coordinates;
}
