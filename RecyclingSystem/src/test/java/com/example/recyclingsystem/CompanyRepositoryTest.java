package com.example.recyclingsystem;

import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.CompanyRepository;
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
public class CompanyRepositoryTest {
    @Autowired
    CompanyRepository companyRepository;

    User user;
    Company company;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal12", "Amal_1234", "User", null, null);
        company = new Company(null, "company@gmail.com",user,null);
    }

    @Test
    public void findCompanyByIdTest() {
        companyRepository.save(company);
        Company company1 = companyRepository.findCompanyById(company.getId());
        Assertions.assertThat(company1).isEqualTo(company);
    }
}
