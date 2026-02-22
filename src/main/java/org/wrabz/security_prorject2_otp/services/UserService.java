package org.wrabz.security_prorject2_otp.services;

import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wrabz.security_prorject2_otp.helper.GenerateCodeUtil;
import org.wrabz.security_prorject2_otp.model.Otp;
import org.wrabz.security_prorject2_otp.model.User;
import org.wrabz.security_prorject2_otp.repositories.OtpRepository;
import org.wrabz.security_prorject2_otp.repositories.UserRepository;

import java.util.Optional;

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

    public void authenticate(User user) {
        Optional<User> o =
                userRepository.findUserByUsername(user.getUsername());
        if (o.isPresent()) {
            User u = o.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                renewOtp(u);
            }else {
                throw new UsernameNotFoundException("Invalid username or password");
            }
        }else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp =
                otpRepository.findOtpByUsername(
                        otpToValidate.getUsername());
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();

        Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());

        Otp otp;
        if (userOtp.isPresent()) {
            otp = userOtp.get();
        } else {
            otp = new Otp();
            otp.setUsername(u.getUsername());
            otpRepository.save(otp);
        }
        otp.setCode(code);
    }
}
