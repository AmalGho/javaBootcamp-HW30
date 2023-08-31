package com.example.recyclingsystem;

import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepositoryTest {
    @Autowired
    AuthRepository authRepository;
    User user;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal12", "Amal_1234", "User", null, null);
    }

    @Test
    public void findUserByIdTest() {
        authRepository.save(user);
        User user1 = authRepository.findUserById(user.getId());
        Assertions.assertThat(user1).isEqualTo(user);
    }

    @Test
    public void findUserByUsernameTest() {
        authRepository.save(user);
        User user1 = authRepository.findUserByUsername(user.getUsername());
        Assertions.assertThat(user1).isEqualTo(user);
    }
}
