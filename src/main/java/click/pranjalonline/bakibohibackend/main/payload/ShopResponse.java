package click.pranjalonline.bakibohibackend.main.payload;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import lombok.Data;

@Data
public class ShopResponse {
    private long id;
    private String name;
    private String address;
    private String image_path;
    private String coordinates;
    private long shopkeeper_id;

    public ShopResponse(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.address = shop.getAddress();
        this.image_path = shop.getImage_path();
        this.coordinates =shop.getCoordinates();
        this.shopkeeper_id= shop.getShopkeeper().getId();
    }
}
