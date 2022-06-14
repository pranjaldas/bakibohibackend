package click.pranjalonline.bakibohibackend.persistance.service.impl;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import click.pranjalonline.bakibohibackend.persistance.repository.ShopRepository;
import click.pranjalonline.bakibohibackend.persistance.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepository shopRepository;
    @Override
    public List<Shop> findAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public boolean createShop(Shop shop) {
        Shop shop1= shopRepository.save(shop);
        if(shop1 != null)
            return true;
        else
            return  false;
    }

    @Override
    public List<Shop> findByShopkeeperId(long id) {
        return shopRepository.findByShopkeeperId(id);
    }

    @Override
    public Shop findById(long id) {
        Optional<Shop> shop= shopRepository.findById(id);
        return shop.isPresent()? shop.get(): null;
    }

    @Override
    public Shop updateShop(long id, Shop shop) {
       Shop shop1= shopRepository.save(shop);
       return shop;

    }

}
