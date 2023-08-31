package com.example.recyclingsystem;

import com.example.recyclingsystem.DTO.ResidentDTO;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.RequestDetail;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import com.example.recyclingsystem.Repository.RequestDetailRepository;
import com.example.recyclingsystem.Repository.RequestRepository;
import com.example.recyclingsystem.Repository.ResidentRepository;
import com.example.recyclingsystem.Service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @InjectMocks
    RequestService requestService;

    @Mock
    RequestRepository requestRepository;
    @Mock
    RequestDetailRepository requestDetailRepository;
    @Mock
    ResidentRepository residentRepository;
    @Mock
    AuthRepository authRepository;

    User user;
    Resident resident;

    List<Request> requests;
    Request request1;
    Request request2;
    RequestDetail requestDetail;

    @BeforeEach
    void setUp() {
        user = new User(1, "amal12", "Amal_1234", "User", null, null);
        resident = new Resident(1, "amal@gmail.com", 0, 0.0, user, null);
        request1 = new Request(null, "paper", 20.0, resident, null, null);
        request2 = new Request(null, "plastic", 20.0, resident, null, null);

        requests = new ArrayList<>();
        requests.add(request1);
        requests.add(request2);
    }

    @Test
    public void addRequestTest() {
        when(authRepository.findUserById(user.getId())).thenReturn(user);

        requestService.addRequest(user.getId(), request1);

        verify(authRepository, times(1)).findUserById(user.getId());
        verify(requestRepository, times(1)).save(request1);
        verify(requestDetailRepository, times(1)).save(request1.getRequestDetail());
        verify(residentRepository, times(1)).save(user.getResident());
    }
}
