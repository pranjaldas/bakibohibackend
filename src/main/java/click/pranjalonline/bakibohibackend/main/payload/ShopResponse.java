package click.pranjalonline.bakibohibackend.main.payload;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {
    private long id;
    private String name;
    private String address;
    private String image_path;
    private String coordinates;

    public ShopResponse(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.address = shop.getAddress();
        this.image_path = shop.getImage_path();
        this.coordinates =shop.getCoordinates();
    }
}
