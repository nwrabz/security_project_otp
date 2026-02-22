package org.wrabz.security_prorject2_otp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wrabz.security_prorject2_otp.model.User;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<User, String> {

    Optional<User> findOtpByUsername(String name);
}
