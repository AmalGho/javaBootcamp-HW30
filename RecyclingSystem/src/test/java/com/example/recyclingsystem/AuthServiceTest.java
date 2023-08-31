package com.example.recyclingsystem;

import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import com.example.recyclingsystem.Service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    AuthService authService;

    @Mock
    AuthRepository authRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal123", "Amal_1234", "RESIDENT", null, null);
    }

    @Test
    public void residentRegister() {
        authService.residentRegister(user);
        verify(authRepository, times(1)).save(user);
    }
}
