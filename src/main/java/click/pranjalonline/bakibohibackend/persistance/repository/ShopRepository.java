package click.pranjalonline.bakibohibackend.persistance.repository;

import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
