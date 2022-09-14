package pl.meating.meatingback.web;

import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.HANARowStoreDialect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.meating.meatingback.user.User;
import pl.meating.meatingback.user.UserService;
import pl.meating.meatingback.user.dto.LoginRequest;
import pl.meating.meatingback.user.dto.RegisterRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest userRegistrationDto) throws Exception {
        User savedUser = userService.register(userRegistrationDto);
        if(savedUser == null){
            return null;
        }
    return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication;
        try{
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(BadCredentialsException e){
            throw new Exception("Nieprawidłowe dane");
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @PostMapping("delete")
    public void delete(String username){
        userService.deleteUser(username);
    }

    @GetMapping("getall")
    public List<User> getUsers(){
        return userService.getAll();
    }
    @GetMapping("get")
    public User getOne(){
        return userService.getOne();
    }
}
