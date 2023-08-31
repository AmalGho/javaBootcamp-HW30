package com.example.recyclingsystem;

import com.example.recyclingsystem.Controller.RequestController;
import com.example.recyclingsystem.DTO.ResidentDTO;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.RequestService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RequestController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class RequestControllerTest {
    @MockBean
    RequestService requestService;

    @Autowired
    MockMvc mockMvc;

    User user;
    Resident resident;

    Request request1, request2;
    List<Request> requests, requestList;

    @BeforeEach
    void setUp() {
        user=new User(null,"Maha" , "12345" , "ADMIN" , null, null);
        resident = new Resident(null, "amal@gmail.com",0,0.0, user, null);

        request1 = new Request(null, "paper", 20.0, resident, null,null);
        request2 = new Request(null, "plastic", 20.0, null, null,null);

        requests= Arrays.asList(request1);
        requestList=Arrays.asList(request2);
    }

    @Test
    public void getResidentRequestsTest() throws Exception {
        Mockito.when(requestService.getResidentRequests(user.getId())).thenReturn(requests);
        mockMvc.perform(get("/api/v1/request/getUserRequests", user.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].waste_type").value("paper"));
    }
}
