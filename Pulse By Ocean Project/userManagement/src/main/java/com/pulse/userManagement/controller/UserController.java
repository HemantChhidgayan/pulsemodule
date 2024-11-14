package com.pulse.userManagement.controller;


import com.pulse.userManagement.model.User;
import com.pulse.userManagement.service.LoginRequest;
import com.pulse.userManagement.service.RegisterRequest;
import com.pulse.userManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1.1 Register User
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest.getAge() + " " + registerRequest.getName()+" " + registerRequest.getPassword()
                + " " + registerRequest.getEmail() + " " + registerRequest.getBloodGroup() + " " + registerRequest.getLocation());
        try {
            User registeredUser = userService.registerUser(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
    // 1.2 Login User
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login Request: " + loginRequest);  // Log for debugging
        try {
            boolean isUserExixt = userService.authenticateUser(loginRequest.getEmailOrPhone(), loginRequest.getPassword());
            if (isUserExixt) {
                return ResponseEntity.ok("user Exist");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email/phone or password.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        }
    }
    @GetMapping(value = "/profile/{email}", produces = "application/json")
    public ResponseEntity<?> getUserProfile(@PathVariable("email") String email) {
        User user = userService.getUserProfile(email);
        try{
        if (user!=null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }}
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<User> editProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
        User editedUser = userService.editProfile(userId, updatedUser);
        return ResponseEntity.ok(editedUser);
    }

    @DeleteMapping(value="deleteProfile/{userId}",produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
