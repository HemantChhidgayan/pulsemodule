package com.pulse.userManagement.service;

import jakarta.validation.constraints.NotBlank;



public class RegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String bloodGroup;

    @NotBlank
    private int age;

    @NotBlank
    private String gender;

    @NotBlank
    private String location;

    public RegisterRequest() {
    }

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getBloodGroup() {
    return bloodGroup;
}

public void setBloodGroup(String bloodGroup) {
    this.bloodGroup = bloodGroup;
}

public int getAge() {
    return age;
}

public void setAge(int age) {
    this.age = age;
}

public String getGender() {
    return gender;
}

public void setGender(String gender) {
    this.gender = gender;
}

public String getLocation() {
    return location;
}

public void setLocation(String location) {
    this.location = location;
}


}
