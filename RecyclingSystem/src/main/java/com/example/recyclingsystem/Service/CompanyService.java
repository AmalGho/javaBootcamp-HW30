package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.DTO.CompanyDTO;
import com.example.recyclingsystem.DTO.ResidentDTO;
import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import com.example.recyclingsystem.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final AuthRepository authRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void addProfile(Integer company_id, CompanyDTO companyDTO) {
        User user = authRepository.findUserById(company_id);

        Company company = new Company(null, companyDTO.getEmail(), user, null);
        companyRepository.save(company);
    }

    public void updateEmail(Integer id, Company company) {
        Company oldCompany = companyRepository.findCompanyById(id);

        oldCompany.setEmail(company.getEmail());
        companyRepository.save(oldCompany);
    }

    public void deleteAccount(Integer user_id) {
        User user = authRepository.findUserById(user_id);

        authRepository.delete(user);
        companyRepository.delete(user.getCompany());
    }
}
