//package pl.meating.meatingback.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import pl.meating.meatingback.user.UserDao;
//import pl.meating.meatingback.user.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username=authentication.getName();
//        String password=authentication.getCredentials().toString();
//
//        UserDao userDao =userRepository.findByUsername(username).orElseThrow(()->
//                new UsernameNotFoundException("UserDao uciekł albo go zmyśliłeś"));
//        if(passwordEncoder.matches(password, userDao.getPassword())){
//            List<GrantedAuthority> authorities = new ArrayList<>();
//           // authorities.add(new SimpleGrantedAuthority(userDao.getAuthority().getAuthority()));
//            return new UsernamePasswordAuthenticationToken(username,password,authorities);
//        }else{
//            throw new BadCredentialsException("Credentialsi to inwalidzi");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
