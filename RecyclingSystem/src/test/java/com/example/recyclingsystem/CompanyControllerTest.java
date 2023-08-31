package com.example.recyclingsystem;

import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Controller.CompanyController;
import com.example.recyclingsystem.DTO.CompanyDTO;
import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CompanyController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CompanyControllerTest {
    @MockBean
    CompanyService companyService;

    @Autowired
    MockMvc mockMvc;
    Company company1,company2;
    ApiResponse apiResponse;

    User user1;
    User user2;
    Company company;

    CompanyDTO companyDTO1;
    CompanyDTO companyDTO2;
    List<Company> companies,companyList;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "amal12", "Amal_1234", "User", null, null);
        user2 = new User(2, "nora12", "Amal_1234", "User", null, null);

        companyDTO1 = new CompanyDTO(user1.getId(), "amal@gmail.com");
        companyDTO2 = new CompanyDTO(user2.getId(), "amal@gmail.com");

        company1 = new Company(null, companyDTO1.getEmail(),user1, null);
        company2 = new Company(null, companyDTO1.getEmail(),user2, null);

        companies = Arrays.asList(company1);
        companyList = Arrays.asList(company2);
    }


    @Test
    public void getAllCompaniesTest() throws Exception {
        Mockito.when(companyService.getAllCompanies()).thenReturn(companies);
        mockMvc.perform(get("/api/v1/company/getAll"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("amal@gmail.com"));
    }

    @Test
    public void testAddProfile() throws  Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/company/addProfile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(company1)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTEmail() throws  Exception {
        mockMvc.perform(put("/api/v1/company/updateEmail", company1.getId(), company1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(company1)))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteAccount() throws Exception{
        mockMvc.perform(delete("/api/v1/company/delete",company1.getId()))
                .andExpect(status().isOk());
    }
}
