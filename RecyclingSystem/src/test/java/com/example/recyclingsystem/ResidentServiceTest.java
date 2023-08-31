package com.example.recyclingsystem;

import com.example.recyclingsystem.DTO.ResidentDTO;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import com.example.recyclingsystem.Repository.ResidentRepository;
import com.example.recyclingsystem.Service.ResidentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResidentServiceTest {
    @InjectMocks
    ResidentService residentService;

    @Mock
    ResidentRepository residentRepository;
    @Mock
    AuthRepository authRepository;

    User user1;
    User user2;
    Resident resident;

    Resident resident1;
    Resident resident2;
    ResidentDTO residentDTO1;
    ResidentDTO residentDTO2;
    List<Resident> residentList;


    @BeforeEach
    void setUp() {
        user1 = new User(null, "amal12", "Amal_1234", "User", null, null);
        user2 = new User(null, "nora12", "Amal_1234", "User", null, null);

        residentDTO1 = new ResidentDTO("amal@gmail.com",0,0.0,user1.getId());
        residentDTO2 = new ResidentDTO("nora@gmail.com",0,0.0,user1.getId());

        resident1 = new Resident(null, residentDTO1.getEmail(), residentDTO1.getPoints(), residentDTO1.getWallet(), user1, null);
        resident2 = new Resident(null, residentDTO2.getEmail(), residentDTO2.getPoints(), residentDTO2.getWallet(), user2, null);

        residentList = new ArrayList<>();
        residentList.add(resident1);
        residentList.add(resident2);
    }

    @Test
    public void getAllResidentsTest() {
        when(residentRepository.findAll()).thenReturn(residentList);

        List<Resident> residents = residentService.getAllResidents();
        Assertions.assertEquals(residents, residentList);

        verify(residentRepository, times(1)).findAll();
    }


    @Test
    public void addProfileTest() {
        when(authRepository.findUserById(user1.getId())).thenReturn(user1);

        residentService.addProfile(user1.getId(), residentDTO1);

        verify(authRepository,times(1)).findUserById(user1.getId());
        verify(residentRepository,times(1)).save(resident1);
    }

    @Test
    public void updateEmailTest() {
        when(residentRepository.findResidentById(resident1.getId())).thenReturn(resident1);

        residentService.updateEmail(resident1.getId(), residentDTO1);

        verify(residentRepository,times(1)).findResidentById(resident1.getId());
        verify(residentRepository,times(1)).save(resident1);
    }
}
