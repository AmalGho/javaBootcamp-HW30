package com.example.recyclingsystem;

import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.RequestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RequestRepositoryTest {
    @Autowired
    RequestRepository requestRepository;

    Request request;
    Request request1;
    Request request2;

    List<Request> requests;
    User user;
    Resident resident;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal12", "Amal_1234", "User", null, null);
        resident = new Resident(1, "resident@gmail.com",0,0.0,user,null);
        request1 = new Request(null, "paper",20.0,resident,null,null);
        request2 = new Request(null, "plastic",30.0,resident,null,null);

    }

    @Test
    public void findRequestByIdTest() {
        requestRepository.save(request1);
        request = requestRepository.findRequestById(request1.getId());
        Assertions.assertThat(request).isEqualTo(request1);
    }

    @Test
    public void findRequestByResidentTest() {
        requestRepository.save(request1);
        requestRepository.save(request2);

        requests = requestRepository.findRequestByResident(resident.getId());
        Assertions.assertThat(requests.get(0).getResident().getId()).isEqualTo(resident.getId());
    }
}
