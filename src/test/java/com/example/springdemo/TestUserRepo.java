package com.example.springdemo;

import com.example.springdemo.model.State;
import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("John Levy" , "levy@gmail.com" , "0X-1321am21321" , State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

    @Test
    public void addUserWithPendingState(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("John Levy" , "levy@gmail.com" , "0X-1321am21321" );
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExist(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("John Levy" , "levy@gmail.com" , "0X-1321am21321" );
        userRepo.addUser("Quoc" , "quoc@gmail.com" , "0X-13221321" );
        userRepo.addUser("khanh" , "khanh@gmail.com" , "0X-1321am21331231221" );
        assertThat(userRepo.isEmailExist("levy@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("quoC@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("khanh@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("Khanh@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("khanh12@gmail.com")).isFalse();
    }

    @Test
    public void findByEmail(){
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("John Levy" , "levy@gmail.com" , "0X-1321am21321" );
        userRepo.addUser("Quoc" , "quoc@gmail.com" , "0X-13221321" );
        userRepo.addUser("khanh" , "khanh@gmail.com" , "0X-1321am21331231221" );
        assertThat(userRepo.findByEmail("levy@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("quoC@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("Khanh@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("khanh12@gmail.com")).isNotPresent();
    }
}
