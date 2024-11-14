package com.pulse.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String emailOrPhone;
    private String password;



}
