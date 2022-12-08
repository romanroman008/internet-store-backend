package pl.meating.meatingback.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.meating.meatingback.user.dto.RegisterRequest;
import pl.meating.meatingback.user.userdetails.UserInformation;
import pl.meating.meatingback.user.userdetails.UserInformationRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;
    private final PasswordEncoder passwordEncoder;
//    private final PasswordEncoder passwordEncoder;
//    private final Validator validator;


    @Transactional
    public UserDao register(RegisterRequest regi)  {
        if(userRepository.findByUsername(regi.getLogin()).isEmpty()&&userRepository.findByUsername(regi.getLogin()).isEmpty()) {
            UserDao userDao = new UserDao();
            UserInformation userInformation =new UserInformation();
            userDao.setUsername(regi.getLogin());
            userInformation.setFirstName(regi.getFirstName());
            userInformation.setLastName(regi.getLastName());
            userInformation.setBirthday(regi.getBirthday());
            userInformation.setStreet(regi.getStreet());
            userInformation.setStreetNumber((int)regi.getStreetNumber());
            userInformation.setFlatNumber((int)regi.getFlatNumber());
            userInformation.setCity(regi.getCity());
            userInformation.setCountry(regi.getCountry());
            userInformation.setPhone(regi.getPhone());
            userInformation.setEmail(regi.getEmail());
            userDao.setPassword(passwordEncoder.encode(regi.getPassword()));
            //userDao.setAuthority(regi.getAuthority());

            userDao.setUserInformation(userInformation);

            //userInformationRepository.save(userDetails);
           return userRepository.save(userDao);

        }
        return null;


    }
//    public boolean updateUser(UserRegistrationDto updated){
//    }

    @Transactional
    public void deleteUser(String username){
       userRepository.deleteByUsername(username);
    }

    public List<UserDao> getAll(){
        return userRepository.findAll();
    }

    public UserDao getOne(){
        if(userRepository.findByUsername("JarekSzparek").isPresent())
            return userRepository.findByUsername("JarekSzparek").get();
        return null;
    }

    public UserDao getUser(String name){
        if(userRepository.findByUsername(name).isPresent())
            return userRepository.findByUsername(name).get();
        return null;
    }

//    private boolean isCurrentUserAdmin(){
//        return SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getAuthorities().stream()
//                .anyMatch(authority->authority.getAuthority().equals(ADMIN_AUTHORITY));
//    }
}
