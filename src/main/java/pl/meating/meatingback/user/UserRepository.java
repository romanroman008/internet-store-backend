package pl.meating.meatingback.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean deleteByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> getByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
