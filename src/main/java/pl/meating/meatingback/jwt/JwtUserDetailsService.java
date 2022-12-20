package pl.meating.meatingback.jwt;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.meating.meatingback.user.UserDao;
import pl.meating.meatingback.user.UserRepository;
import pl.meating.meatingback.user.dto.RegisterRequest;
import pl.meating.meatingback.user.userdetails.UserInformation;
import pl.meating.meatingback.user.userdetails.UserInformationDto;
import pl.meating.meatingback.user.userdetails.UserInformationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private UserInformationRepository userInformationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDao> list=userRepository.findByUsername(username);
        if(userRepository.findByUsername(username).isEmpty())
            throw new UsernameNotFoundException("User not found with username: " + username);


        UserDao userDao = userRepository.findByUsername(username).get(0);
        return new User(userDao.getUsername(), userDao.getPassword(), new ArrayList<>()) {
        };

    }

    @Transactional
    public UserDao save(RegisterRequest regi)  {
        if(userRepository.findByUsername(regi.getLogin()).isEmpty()&&userRepository.findByUsername(regi.getLogin()).isEmpty()) {
            UserDao userDao = new UserDao();
            UserInformation userInformation = new UserInformation();
            userDao.setUsername(regi.getLogin());
            userInformation.setFirstName(regi.getFirstName());
            userInformation.setLastName(regi.getLastName());
            userInformation.setBirthday(regi.getBirthday());
            userInformation.setStreet(regi.getStreet());
            userInformation.setStreetNumber((int) regi.getStreetNumber());
            userInformation.setFlatNumber((int) regi.getFlatNumber());
            userInformation.setCity(regi.getCity());
            userInformation.setCountry(regi.getCountry());
            userInformation.setPhone(regi.getPhone());
            userInformation.setEmail(regi.getEmail());
            userDao.setPassword(passwordEncoder.encode(regi.getPassword()));

            userDao.setUserInformation(userInformation);


            return userRepository.save(userDao);
        }
       throw new IllegalArgumentException("Użytkownik już istnieje");
    }



}
