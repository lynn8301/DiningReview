package com.diningReview.DiningReview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.diningReview.DiningReview.repository.UserRepository;
import com.diningReview.DiningReview.model.User;

import java.util.Optional;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users/{userName}")
    public Optional<User> getUserByName(@PathVariable("userName") String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist.");
        }

        return userRepository.findByUserName(userName);
    }
    
    @PutMapping("/users/{userName}")
    public User updateUser(@PathVariable("userName") String userName, @RequestBody User userChange) {
        User user = userRepository.findByUserName(userName)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This use does not exist"));
        
        user.setCity(userChange.getCity());
        user.setState(userChange.getState());
        user.setZipCode(userChange.getZipCode());
        user.setPeanutAllergy(userChange.isPeanutAllergy());
        user.setEggAllergy(userChange.isEggAllergy());
        user.setDairyAllergy(userChange.isDairyAllergy());
        
        return userRepository.save(user);
    }
}
