package click.pranjalonline.bakibohibackend.persistance.service.impl;

import click.pranjalonline.bakibohibackend.persistance.entity.User;
import click.pranjalonline.bakibohibackend.persistance.repository.UserRepository;
import click.pranjalonline.bakibohibackend.persistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    public Optional<User> findUserByUsernameOrEmail(String usernameOrEmail){
        // HAVE TO THROW USERNAME NOT FOUND EXCEPTION LATERON
        return repository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);

    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }
}
