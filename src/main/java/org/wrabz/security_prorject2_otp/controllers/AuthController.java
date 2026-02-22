package org.wrabz.security_prorject2_otp.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wrabz.security_prorject2_otp.model.Otp;
import org.wrabz.security_prorject2_otp.model.User;
import org.wrabz.security_prorject2_otp.services.UserService;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public void  addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody User user){
        userService.authenticate(user);
    }

    @PostMapping("/otp/check")
    public void checkOtp(@RequestBody Otp otp, HttpServletResponse response){
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
