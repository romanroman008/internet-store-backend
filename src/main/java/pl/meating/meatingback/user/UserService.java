package pl.meating.meatingback.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.validation.Validator;
import pl.meating.meatingback.user.dto.RegisterRequest;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

//    private static final String USER_ROLE="USER";
//    private static final String ADMIN_AUTHORITY="ROLE_ADMIN";
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final Validator validator;


    @Transactional
    public User register(RegisterRequest regi)  {
        if(userRepository.getByEmail(regi.getEmail()).isEmpty()&&userRepository.findByUsername(regi.getLogin()).isEmpty()) {
            User user = new User();
            user.setUsername(regi.getLogin());
            user.setFirstName(regi.getFirstName());
            user.setLastName(regi.getLastName());
            user.setBirthday(regi.getBirthday());
            user.setStreet(regi.getStreet());
            user.setStreetNumber((int)regi.getStreetNumber());
            user.setFlatNumber((int)regi.getFlatNumber());
            user.setCity(regi.getCity());
            user.setCountry(regi.getCountry());
            user.setPhone(regi.getPhone());
            user.setEmail(regi.getEmail());
            //String passwordHash = passwordEncoder.encode(regi.getPassword());
            user.setPassword(regi.getPassword());
            Optional<UserRole> userRole=userRoleRepository.findByName(ERole.ROLE_USER);
            userRole.ifPresentOrElse(
                    role->user.getRoles().add(role),
                    ()->{
                        throw new NoSuchElementException();
                    }
            );


           return userRepository.save(user);

        }
        return null;


    }
//    public boolean updateUser(UserRegistrationDto updated){
//    }

    public boolean deleteUser(String email){
       return userRepository.deleteByEmail(email);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getOne(){
        if(userRepository.findByUsername("JarekSzparek").isPresent())
            return userRepository.findByUsername("JarekSzparek").get();
        return null;
    }

//    private boolean isCurrentUserAdmin(){
//        return SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getAuthorities().stream()
//                .anyMatch(authority->authority.getAuthority().equals(ADMIN_AUTHORITY));
//    }
}
