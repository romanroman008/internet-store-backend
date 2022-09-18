package pl.meating.meatingback.user.userdetails;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    Optional<UserDetails> getByEmail(String email);
}
