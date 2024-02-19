package com.example.springdemo.repository;

import com.example.springdemo.model.State;
import com.example.springdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String , User> users = new ConcurrentHashMap<>();

    public User addUser(String fullname , String email, String hashed_password){
        return addUser(fullname,email,hashed_password,State.PENDING);
    }
    public User addUser(String fullname , String email, String hashed_password, State state){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .fullname(fullname)
                .email(email)
                .hashed_password(hashed_password)
                .state(state)
                .build();
        users.put(id,user);
        return user;
    }

    public boolean isEmailExist(String email){
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).count() > 0;
    }

    public Optional<User> findByEmail(String email){
        return users.values().stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}
