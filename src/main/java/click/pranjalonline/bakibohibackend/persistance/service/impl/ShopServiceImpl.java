package click.pranjalonline.bakibohibackend.persistance.service.impl;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import click.pranjalonline.bakibohibackend.persistance.repository.ShopRepository;
import click.pranjalonline.bakibohibackend.persistance.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
