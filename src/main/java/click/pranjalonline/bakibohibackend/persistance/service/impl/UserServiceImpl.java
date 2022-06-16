package click.pranjalonline.bakibohibackend.persistance.service.impl;

import click.pranjalonline.bakibohibackend.authentication.payload.UserPayload;
import click.pranjalonline.bakibohibackend.persistance.Constants;
import click.pranjalonline.bakibohibackend.persistance.entity.User;
import click.pranjalonline.bakibohibackend.persistance.repository.RoleRepository;
import click.pranjalonline.bakibohibackend.persistance.repository.UserRepository;
import click.pranjalonline.bakibohibackend.persistance.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger LOGGER= LogManager.getLogger(UserService.class);
    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Optional<User> findUserByUsernameOrEmail(String usernameOrEmail){
        // HAVE TO THROW USERNAME NOT FOUND EXCEPTION LATERON
        return repository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);

    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser= repository.findById(id);
        if(optionalUser.isPresent())
            return optionalUser.get();
        else
            return null;
    }

    @Override
    public User create(UserPayload user) {
        LOGGER.info("Request to register"+user.toString());

        User userToSave=modelMapper.map(user,User.class);
        if(user.getUserType().equals(Constants.ROLE_SHOPKEEPER))
            userToSave.setRoles(Collections.singleton(roleRepository.findByName(Constants.ROLE_ADMIN)));
        else if(user.getUserType().equals(Constants.ROLE_CUSTOMER))
            userToSave.setRoles(Collections.singleton(roleRepository.findByName(Constants.ROLE_CUSTOMER)));
        else
            userToSave.setRoles(Collections.singleton(roleRepository.findByName(Constants.ROLE_ADMIN)));

        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        User savedUser= repository.save(userToSave);
        LOGGER.info("SAVED USER :"+savedUser.toString());
        return savedUser;



    }
}
