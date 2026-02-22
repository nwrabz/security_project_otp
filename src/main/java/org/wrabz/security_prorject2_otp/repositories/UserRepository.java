package org.wrabz.security_prorject2_otp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wrabz.security_prorject2_otp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
}
