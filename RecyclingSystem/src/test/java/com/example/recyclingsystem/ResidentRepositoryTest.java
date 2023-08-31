package com.example.recyclingsystem;

import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.ResidentRepository;
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
public class ResidentRepositoryTest {
    @Autowired
    ResidentRepository residentRepository;

    User user;
    Resident resident;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal12", "Amal_1234", "User", null, null);
        resident = new Resident(null, "resident@gmail.com",0,0.0,user,null);
    }

    @Test
    public void findResidentByIdTest() {
        residentRepository.save(resident);
        Resident resident1 = residentRepository.findResidentById(resident.getId());
        Assertions.assertThat(resident1).isEqualTo(resident);
    }
}
