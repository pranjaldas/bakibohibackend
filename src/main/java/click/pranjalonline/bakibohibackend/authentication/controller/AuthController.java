package click.pranjalonline.bakibohibackend.authentication.controller;

import click.pranjalonline.bakibohibackend.authentication.payload.LoginDto;
import click.pranjalonline.bakibohibackend.authentication.payload.UserPayload;
import click.pranjalonline.bakibohibackend.authentication.security.JwtTokenProvider;
import click.pranjalonline.bakibohibackend.persistance.entity.User;
import click.pranjalonline.bakibohibackend.persistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));

        // GET THE TOKEN
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(token);

    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserPayload registerDto){
       User savedUser= userService.create(registerDto);
       return ResponseEntity.ok(savedUser.toString());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserPayload userPayload, @PathVariable(name="id") long id){
        return ResponseEntity.ok("UPDATED SUCCESFULLY");
    }
}
