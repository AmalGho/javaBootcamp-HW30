package com.example.recyclingsystem.Repository;

import com.example.recyclingsystem.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findCompanyById(Integer id);
}
