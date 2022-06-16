package click.pranjalonline.bakibohibackend.persistance.repository;

import click.pranjalonline.bakibohibackend.persistance.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String roleUser);
}
