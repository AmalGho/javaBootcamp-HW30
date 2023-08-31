package com.example.recyclingsystem.Controller;


import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.DTO.RequestDetailDTO;
import com.example.recyclingsystem.Model.RequestDetail;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.RequestDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detail")
public class RequestDetailController {

    private final RequestDetailService requestDetailService;

    @GetMapping("/getAll")
    public ResponseEntity getAllRequestsDetail() {
        return ResponseEntity.status(HttpStatus.OK).body(requestDetailService.getAllRequestDetail());
    }

    @GetMapping("/getDetail/{request_id}")
    public ResponseEntity getRequestDetail(@PathVariable Integer request_id) {
        return ResponseEntity.status(HttpStatus.OK).body(requestDetailService.getRequestDetail(request_id));
    }


//    not need after relations made

//    @PostMapping("/add")
//    public ResponseEntity addRequestDetail(@RequestBody @Valid RequestDetailDTO requestDetailDTO) {
//        requestDetailService.addRequestDetail(requestDetailDTO);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("request detail Added"));
//    }
//
//    @DeleteMapping("/delete/{request_id}")
//    public ResponseEntity deleteDetails(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {
//        requestDetailService.deleteRequestDetail(user.getResident().getId(), request_id);
//        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("detail deleted"));
//    }

}
