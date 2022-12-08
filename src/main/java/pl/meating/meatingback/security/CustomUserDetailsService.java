package pl.meating.meatingback.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.meating.meatingback.user.UserDao;
import pl.meating.meatingback.user.UserRepository;

import java.util.ArrayList;

//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDao userDao =userRepository.findByUsername(username).orElseThrow(
//                ()->new UsernameNotFoundException("UserDao not found"));
//        return new org.springframework.security.core.userdetails.User(
//                userDao.getUsername(), userDao.getPassword(),new ArrayList<>());
//    }
//}
