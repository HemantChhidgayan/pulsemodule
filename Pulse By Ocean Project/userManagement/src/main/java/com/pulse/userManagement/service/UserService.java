package com.pulse.userManagement.service;

import com.pulse.userManagement.Repository.UserRepository;
import com.pulse.userManagement.model.User;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;


@Configuration
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(@Valid RegisterRequest registerRequest) {


        User user = new User(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getBloodGroup(),
                registerRequest.getAge(),
                registerRequest.getGender(),
                registerRequest.getLocation()
        );
        userRepository.save(user);
        return user;
    }

    public User clone(String emailOrPhone, String password) {
        //String email = userRepository.findbyEmail(emailOrPhone);
        return new User();
    }

    public boolean authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).isPresent();
    }

    public User getUserProfile(String email) {
        Optional<User> user= userRepository.findByEmail(email);
        return user.orElse(null);
    }
    //@Transactional
    public User editProfile(Long userId, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(userId);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update fields as necessary
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
           // existingUser.setPassword(updatedUser.getPassword());
            existingUser.setBloodGroup(updatedUser.getBloodGroup());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setLocation(updatedUser.getLocation());
            // Add other fields you want to allow editing

            return userRepository.save(existingUser); // Save updated user
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }
}