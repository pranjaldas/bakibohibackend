package click.pranjalonline.bakibohibackend.persistance.service;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> findAllShops();
    boolean createShop(Shop shop);
    List<Shop> findByShopkeeperId(long id);
    Shop findById(long id);
    Shop updateShop(long id,Shop shop);
}
