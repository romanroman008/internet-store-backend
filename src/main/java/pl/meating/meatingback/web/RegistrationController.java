package pl.meating.meatingback.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.meating.meatingback.user.User;
import pl.meating.meatingback.user.UserService;
import pl.meating.meatingback.user.dto.RegisterRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest userRegistrationDto) throws Exception {
        User savedUser = userService.register(userRegistrationDto);
        if(savedUser == null){
            return null;
        }
    return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("delete")
    public void delete(String email){
        userService.deleteUser(email);
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
