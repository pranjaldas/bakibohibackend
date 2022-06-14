package click.pranjalonline.bakibohibackend.persistance.repository;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    List<Shop> findByShopkeeperId(long id);
}
