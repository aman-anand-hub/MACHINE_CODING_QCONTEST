package com.crio.qcontest.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.qcontest.constants.UserOrder;
import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IUserRepository;
import com.crio.qcontest.repositories.UserRepository;

public class UserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name) {
        Optional<User> us= userRepository.findByName(name);
        if(us.isPresent() == true)
        {
            throw new RuntimeException("This user already exists");        
        }
        User u= new User(name);
        return userRepository.save(u);
    }

    public List<User> getUsers(UserOrder userOrder) {
        List<User> res = userRepository.findAll();
        if(res.isEmpty())
        { 
            throw new RuntimeException("No users registered");
        }
        Collections.sort(res, Comparator.comparing(User::getTotalScore));

        if (userOrder.equals(UserOrder.SCORE_DESC))
        {
            Collections.reverse(res);
        }
        return res;
    } 
}
