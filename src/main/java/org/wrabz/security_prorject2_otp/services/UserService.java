package org.wrabz.security_prorject2_otp.services;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wrabz.security_prorject2_otp.model.User;
import org.wrabz.security_prorject2_otp.repositories.OtpRepository;
import org.wrabz.security_prorject2_otp.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OtpRepository otpRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpRepository = otpRepository;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
