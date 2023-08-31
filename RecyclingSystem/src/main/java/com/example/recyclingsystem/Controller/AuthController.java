package com.example.recyclingsystem.Controller;

import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/adminRegister")
    public ResponseEntity adminRegister(@RequestBody @Valid User user) {
        authService.adminRegister(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("admin login success"));
    }

    @PostMapping("/residentRegister")
    public ResponseEntity residentRegister(@RequestBody @Valid User user) {
        authService.residentRegister(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account created successfully"));
    }

    @PostMapping("/companyRegister")
    public ResponseEntity companyRegister(@RequestBody @Valid User user) {
        authService.companyRegister(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account created successfully"));
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body(new ApiResponse("logout successfully"));
    }
}
