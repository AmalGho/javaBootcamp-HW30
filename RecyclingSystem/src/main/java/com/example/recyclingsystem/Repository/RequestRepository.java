package com.example.recyclingsystem.Repository;

import com.example.recyclingsystem.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findRequestById(Integer id);

    @Query("select r from Request r where r.resident.id=?1")
    List<Request> findRequestByResident(Integer resident_id);

    @Query("select r from Request r where r.company.id=?1")
    List<Request> findRequestByCompany(Integer company_id);

    @Query("select r from Request r where r.resident.id=?1 and r.id=?2")
    Request findRequestByResidentAndId(Integer resident_id, Integer request_id);

    @Query("select r from Request r where r.company.id=?1 and r.id=?2")
    Request findRequestByCompanyAndId(Integer company_id, Integer request_id);

}
