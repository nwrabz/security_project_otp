package org.wrabz.security_prorject2_otp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Setter
@Getter
public class User {
    @Id
    private String username;
    private String password;
}
