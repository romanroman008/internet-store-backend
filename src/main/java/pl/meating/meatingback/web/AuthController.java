//package pl.meating.meatingback.web;
//
//
//import lombok.RequiredArgsConstructor;
//import org.apache.coyote.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import pl.meating.meatingback.security.jwt.JwtUtils;
//import pl.meating.meatingback.user.*;
//import pl.meating.meatingback.user.dto.LoginRequest;
//import pl.meating.meatingback.user.dto.RegisterRequest;
//import pl.meating.meatingback.user.response.JwtResponse;
//import pl.meating.meatingback.user.userdetails.UserDetailsImpl;
//
//import javax.validation.Valid;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@CrossOrigin(origins="*",maxAge = 3600)
//@RestController
//@RequestMapping("auth")
//public class AuthController {
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserRoleRepository roleRepository;
//    @Autowired
//    PasswordEncoder encoder;
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
//        Authentication authentication=authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(),loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt= jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles=userDetails.getAuthorities().stream()
//                .map(item->item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));
//    }
//
//    @PostMapping("signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
//        if(userRepository.existsByUsername(registerRequest.getLogin())){
//            return ResponseEntity
//                    .badRequest()
//                    .body("error1!!1");
//        }
//        if(userRepository.existsByEmail(registerRequest.getEmail())){
//            return ResponseEntity
//                    .badRequest()
//                    .body("chasd");
//        }
//
//        User user=new User(registerRequest.getLogin(),
//                encoder.encode(registerRequest.getPassword()),
//                registerRequest.getFirstName(),
//                registerRequest.getLastName(),
//                registerRequest.getBirthday(),
//                registerRequest.getStreet(),
//                (int)registerRequest.getStreetNumber(),
//                (int)registerRequest.getFlatNumber(),
//                registerRequest.getCity(),
//                registerRequest.getCountry(),
//                registerRequest.getPhone(),
//                registerRequest.getEmail());
//
//        Set<String> strRoles=registerRequest.getRoles();
//        Set<UserRole> roles=new HashSet<>();
//
//        if(strRoles==null){
//            UserRole userRole=roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(()->new RuntimeException("Error: Role is not found"));
//            roles.add(userRole);
//        }else{
//            strRoles.forEach(role->{
//                switch(role){
//                    case "admin":
//                        UserRole adminRole=roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
//                        roles.add(adminRole);
//                        break;
//                    default:
//                        UserRole userRole=roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(()->new RuntimeException("Error: Role is not found"));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok("User registred succesfully");
//    }
//}
