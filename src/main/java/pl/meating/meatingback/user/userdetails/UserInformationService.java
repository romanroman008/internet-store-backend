package pl.meating.meatingback.user.userdetails;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInformationService {
   private final UserInformationRepository userInformationRepository;

   @Transactional
   public UserInformationDto saveUserDetails(UserInformationDto userInformationDto){
      if(userInformationRepository.getByEmail(userInformationDto.getEmail()).isEmpty()) {
         UserInformation ownUserInformation = new UserInformation();
         ownUserInformation.setFirstName(userInformationDto.getFirstName());
         ownUserInformation.setLastName(userInformationDto.getLastName());
         ownUserInformation.setBirthday(userInformationDto.getBirthday());
         ownUserInformation.setStreet(userInformationDto.getStreet());
         ownUserInformation.setStreetNumber((int) userInformationDto.getStreetNumber());
         ownUserInformation.setFlatNumber((int) userInformationDto.getFlatNumber());
         ownUserInformation.setCity(userInformationDto.getCity());
         ownUserInformation.setCountry(userInformationDto.getCountry());
         ownUserInformation.setPhone(userInformationDto.getPhone());
         ownUserInformation.setEmail(userInformationDto.getEmail());
         userInformationRepository.save(ownUserInformation);
         return userInformationDto;
      }
      return null;
   }
}
