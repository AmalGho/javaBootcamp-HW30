package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.DTO.RequestDetailDTO;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.RequestDetail;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import com.example.recyclingsystem.Repository.RequestDetailRepository;
import com.example.recyclingsystem.Repository.RequestRepository;
import com.example.recyclingsystem.Repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestDetailService {
    private final RequestDetailRepository requestDetailRepository;
    private final RequestRepository requestRepository;

    public List<RequestDetail> getAllRequestDetail() {
        return requestDetailRepository.findAll();
    }

    public RequestDetail getRequestDetail(Integer request_id){
        Request request = requestRepository.findRequestById(request_id);
        if (request == null) throw new ApiException("no requests yet");

        return request.getRequestDetail();
    }


    //    not need after relations made

//    public void addRequestDetail(RequestDetailDTO requestDetailDTO) {
//        Request request = requestRepository.findRequestById(requestDetailDTO.getRequest_id());
//        if (request == null) throw new ApiException("this request not exist");
//
//        RequestDetail requestDetail = new RequestDetail(null,requestDetailDTO.getRequest_code(), requestDetailDTO.getStatus(), request);
//        requestDetailRepository.save(requestDetail);
//    }
//
//
//    public void deleteRequestDetail(Integer resident_id, Integer request_id) {
//        Request request = requestRepository.findRequestByResidentAndId(resident_id, request_id);
//        if (request == null) throw new ApiException("no requests to delete");
//        requestRepository.delete(request);
//        requestDetailRepository.delete(request.getRequestDetail());
//    }

}
