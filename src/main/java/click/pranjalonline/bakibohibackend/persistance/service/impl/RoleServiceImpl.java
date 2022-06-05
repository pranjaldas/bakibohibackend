package click.pranjalonline.bakibohibackend.persistance.service.impl;

import click.pranjalonline.bakibohibackend.persistance.repository.RoleRepository;
import click.pranjalonline.bakibohibackend.persistance.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

}
