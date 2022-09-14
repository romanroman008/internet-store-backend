package pl.meating.meatingback.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.validation.Validator;
import pl.meating.meatingback.user.dto.RegisterRequest;
import pl.meating.meatingback.user.userdetails.UserDetails;
import pl.meating.meatingback.user.userdetails.UserDetailsRepository;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
//    private final PasswordEncoder passwordEncoder;
//    private final Validator validator;


    @Transactional
    public User register(RegisterRequest regi)  {
        if(userRepository.findByUsername(regi.getLogin()).isEmpty()&&userRepository.findByUsername(regi.getLogin()).isEmpty()) {
            User user = new User();
            UserDetails userDetails=new UserDetails();
            user.setUsername(regi.getLogin());
            userDetails.setFirstName(regi.getFirstName());
            userDetails.setLastName(regi.getLastName());
            userDetails.setBirthday(regi.getBirthday());
            userDetails.setStreet(regi.getStreet());
            userDetails.setStreetNumber((int)regi.getStreetNumber());
            userDetails.setFlatNumber((int)regi.getFlatNumber());
            userDetails.setCity(regi.getCity());
            userDetails.setCountry(regi.getCountry());
            userDetails.setPhone(regi.getPhone());
            userDetails.setEmail(regi.getEmail());
            user.setPassword(passwordEncoder.encode(regi.getPassword()));

            user.setUserDetails(userDetails);

            //userDetailsRepository.save(userDetails);
           return userRepository.save(user);

        }
        return null;


    }
//    public boolean updateUser(UserRegistrationDto updated){
//    }

    @Transactional
    public void deleteUser(String username){
       userRepository.deleteByUsername(username);
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
