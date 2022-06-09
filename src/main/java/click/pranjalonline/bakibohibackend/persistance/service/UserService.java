package click.pranjalonline.bakibohibackend.persistance.service;

import click.pranjalonline.bakibohibackend.persistance.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsernameOrEmail(String userNameOrEmail);
    boolean existsByUsername(String username);
    User findById(Long id);
}
