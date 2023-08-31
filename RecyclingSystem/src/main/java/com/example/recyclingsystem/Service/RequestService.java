package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.DTO.RequestDetailDTO;
import com.example.recyclingsystem.Model.*;
import com.example.recyclingsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestDetailRepository requestDetailRepository;
    private final ResidentRepository residentRepository;
    private final CompanyRepository companyRepository;
    private final AuthRepository authRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request> getResidentRequests(Integer resident_id) {
        List<Request> requests = requestRepository.findRequestByResident(resident_id);
        if (requests == null || requests.isEmpty()) throw new ApiException("no requests yet");
        return requests;
    }

    public void addRequest(Integer resident_id, Request request) {
        User user = authRepository.findUserById(resident_id);


        RequestDetail requestDetail = new RequestDetail(null,"pending", LocalDateTime.now(), request);
        request.setResident(user.getResident());
        request.setRequestDetail(requestDetail);
        requestRepository.save(request);
        requestDetailRepository.save(requestDetail);
//        user.getResident().setPoints(user.getResident().getPoints()+5);
        residentRepository.save(user.getResident());
    }


    public void updateRequest(Integer resident_id, Integer request_id, Request request) {
        User user = authRepository.findUserById(resident_id);
        Request oldRequest = requestRepository.findRequestByResidentAndId(user.getId(), request_id);
        if (oldRequest == null) throw new ApiException("you cannot modify this request");

        Boolean isPending = oldRequest.getRequestDetail().getStatus().equalsIgnoreCase("pending");

        if (isPending) {
            oldRequest.setWaste_type(request.getWaste_type());
            oldRequest.setWeight(request.getWeight());
            requestRepository.save(oldRequest);
        }else throw new ApiException("you cannot modify confirmed request");

    }


    public void deleteRequest(Integer resident_id, Integer request_id) {
        User user = authRepository.findUserById(resident_id);
        Request oldRequest = requestRepository.findRequestByResidentAndId(user.getResident().getId(), request_id);
        if (oldRequest == null) throw new ApiException("you cannot delete this request");

        Boolean isPending = oldRequest.getRequestDetail().getStatus().equalsIgnoreCase("pending");

        if (isPending) {
            requestRepository.delete(oldRequest);
            requestDetailRepository.delete(oldRequest.getRequestDetail());
        }else throw new ApiException("you cannot modify confirmed request");

    }

    public List<Request> getCompanyPurchases(Integer company_id) {
        List<Request> requests = requestRepository.findRequestByCompany(company_id);
        if (requests == null || requests.isEmpty()) throw new ApiException("no purchases yet");
        return requests;
    }


    public void companyBuyRequest(Integer request_id, Integer company_id) {
        User user = authRepository.findUserById(company_id);
        Request request = requestRepository.findRequestByCompanyAndId(user.getId(), request_id);
        if (request == null) throw new ApiException("request not found");

        Boolean isPending = request.getRequestDetail().getStatus().equalsIgnoreCase("pending");

        if (isPending) {
            request.setCompany(user.getCompany());
            request.getRequestDetail().setStatus("confirmed");
            requestRepository.save(request);
        }else throw new ApiException("this request not available to purchase");

    }




    public void assignResidentToRequest(Integer request_id, Integer resident_id) {
        Request request = requestRepository.findRequestById(request_id);
        Resident resident = residentRepository.findResidentById(resident_id);

        if (request == null || resident == null)
            throw new ApiException("cannot assign request to resident");

        request.setResident(resident);
        requestRepository.save(request);
    }

    public void assignCompanyToRequest(Integer request_id, Integer company_id) {
        Request request = requestRepository.findRequestById(request_id);
        Company company = companyRepository.findCompanyById(company_id);

        if (request == null || company == null)
            throw new ApiException("cannot assign request to resident");

        request.setCompany(company);
        requestRepository.save(request);
    }

}
