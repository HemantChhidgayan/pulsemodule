package com.pulse.service;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class RegisterRequest {
    @Getter @Setter
    @NotBlank
    private String name;

    @Getter @Setter
    @NotBlank
    private String email;

    @Getter @Setter
    @NotBlank
    private String password;

    @Getter @Setter
    @NotBlank
    private String bloodGroup;

    @Getter @Setter
    @NotBlank
    private int age;

    @Getter @Setter
    @NotBlank
    private String gender;

    @Getter @Setter
    @NotBlank
    private String location;

    public RegisterRequest() {
    }

}
