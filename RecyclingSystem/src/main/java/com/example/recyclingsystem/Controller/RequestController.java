package com.example.recyclingsystem.Controller;

import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/getAll")
    public ResponseEntity getAllRequests() {
        return ResponseEntity.status(HttpStatus.OK).body(requestService.getAllRequests());
    }

    @GetMapping("/getUserRequests")
    public ResponseEntity getResidentRequests(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(requestService.getResidentRequests(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addRequest(@AuthenticationPrincipal User user, @RequestBody @Valid Request request) {
        requestService.addRequest(user.getId(), request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request added"));
    }

    @PutMapping("/update/{request_id}")
    public ResponseEntity updateRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id, @RequestBody @Valid Request request) {
        requestService.updateRequest(user.getId(), request_id, request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request updated"));
    }

    @DeleteMapping("/delete/{request_id}")
    public ResponseEntity deleteRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {
        requestService.deleteRequest(user.getId(), request_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request deleted"));
    }

    @PutMapping("/buyRequest/{request_id}")
    public ResponseEntity companyBuyRequest(@PathVariable Integer request_id, @AuthenticationPrincipal User user){
        requestService.companyBuyRequest(request_id, user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request purchased"));
    }

    @GetMapping("/getCompanyPurchases")
    public ResponseEntity getCompanyPurchases(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(requestService.getCompanyPurchases(user.getCompany().getId()));
    }
}
