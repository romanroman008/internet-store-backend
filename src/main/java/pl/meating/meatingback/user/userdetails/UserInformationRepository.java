package pl.meating.meatingback.user.userdetails;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation,Long> {
    Optional<UserInformation> getByEmail(String email);
}
