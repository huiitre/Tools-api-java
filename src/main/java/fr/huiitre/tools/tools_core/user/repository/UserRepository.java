package fr.huiitre.tools.tools_core.user.repository;

import fr.huiitre.tools.tools_core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByName(String name);
    User findByRememberToken(String token);
    User findByGoogleId(String googleId);
}
