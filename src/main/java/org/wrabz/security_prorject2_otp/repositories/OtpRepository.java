package org.wrabz.security_prorject2_otp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wrabz.security_prorject2_otp.model.Otp;
import org.wrabz.security_prorject2_otp.model.User;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

    Optional<Otp> findOtpByUsername(String name);
}
