package pl.meating.meatingback.user.userdetails;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
   private final UserDetailsRepository userDetailsRepository;

   @Transactional
   public UserDetailsDto saveUserDetails(UserDetailsDto userDetailsDto){
      if(userDetailsRepository.getByEmail(userDetailsDto.getEmail()).isEmpty()) {
         UserDetails ownUserDetails = new UserDetails();
         ownUserDetails.setFirstName(userDetailsDto.getFirstName());
         ownUserDetails.setLastName(userDetailsDto.getLastName());
         ownUserDetails.setBirthday(userDetailsDto.getBirthday());
         ownUserDetails.setStreet(userDetailsDto.getStreet());
         ownUserDetails.setStreetNumber((int) userDetailsDto.getStreetNumber());
         ownUserDetails.setFlatNumber((int) userDetailsDto.getFlatNumber());
         ownUserDetails.setCity(userDetailsDto.getCity());
         ownUserDetails.setCountry(userDetailsDto.getCountry());
         ownUserDetails.setPhone(userDetailsDto.getPhone());
         ownUserDetails.setEmail(userDetailsDto.getEmail());
         userDetailsRepository.save(ownUserDetails);
         return userDetailsDto;
      }
      return null;
   }
}
