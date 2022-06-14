package click.pranjalonline.bakibohibackend.authentication.controller;

import click.pranjalonline.bakibohibackend.authentication.payload.LoginDto;
import click.pranjalonline.bakibohibackend.authentication.security.JwtTokenProvider;
import click.pranjalonline.bakibohibackend.persistance.entity.Shop;
import click.pranjalonline.bakibohibackend.persistance.entity.User;
import click.pranjalonline.bakibohibackend.persistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping(value = "/getuser")
    public ResponseEntity<User> getUser(){
        Optional<User> user= userService.findUserByUsernameOrEmail("pranjaldas");
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            throw new RuntimeException("User not found");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));

        // GET THE TOKEN
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(token);

    }
    @GetMapping(value = "/test")
    public ResponseEntity<User> test(){
        User user= userService.findById(1L);
        return  ResponseEntity.ok(user);
    }
}
