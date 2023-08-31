package com.example.recyclingsystem.Repository;

import com.example.recyclingsystem.Model.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail, Integer> {
    RequestDetail findRequestDetailById(Integer id);

}
