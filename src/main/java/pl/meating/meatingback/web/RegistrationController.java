package pl.meating.meatingback.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.meating.meatingback.user.UserDao;
import pl.meating.meatingback.user.UserService;
import pl.meating.meatingback.user.dto.LoginRequest;
import pl.meating.meatingback.user.dto.RegisterRequest;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping(path="/basicauth")
    public String test(){
        return "You are authenticated";
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserDao> register(@Valid @RequestBody RegisterRequest userRegistrationDto) throws Exception {
        UserDao savedUserDao = userService.register(userRegistrationDto);
        if(savedUserDao == null){
            return null;
        }
    return new ResponseEntity<UserDao>(savedUserDao, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Authentication> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication;
       // UserDao user;
        try{
            authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //user=userService.getUserDao(loginRequest.getLogin());
        }catch(BadCredentialsException e){
            throw new Exception("Nieprawidłowe dane");
             //return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Authentication>(authentication,HttpStatus.OK);
    }

    @PostMapping("delete")
    public void delete(String username){
        userService.deleteUser(username);
    }

    @GetMapping("getall")
    public List<UserDao> getUsers(){
        return userService.getAll();
    }
    @GetMapping("get")
    public UserDao getOne(){
        return userService.getOne();
    }
}
