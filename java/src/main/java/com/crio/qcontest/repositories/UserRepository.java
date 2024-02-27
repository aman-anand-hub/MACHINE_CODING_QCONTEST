package com.crio.qcontest.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.qcontest.entities.User;

public class UserRepository implements IUserRepository{
    private final Map<Long,User> userMap;
    private Long autoIncrement = 1L;

    public UserRepository(){
        userMap = new HashMap<Long,User>();
    }

    @Override
    public User save(User user) {
        User new_user=null;
        if(user.getId()==null){
           new_user=new User(user.getName(),autoIncrement);
           
        }else{
            new_user=user;
        }
        userMap.put(autoIncrement,new_user);
        autoIncrement++;
     return new_user;
    }

    @Override
    public List<User> findAll() {
        List<User> res= new ArrayList<>();
        userMap.forEach((key, value) -> {
            res.add(value);
        });
        return res;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
    // Find an user which matches with the required name.
        return userMap.values().stream().filter(u -> u.getName().equals(name)).findFirst();
    }  
}
