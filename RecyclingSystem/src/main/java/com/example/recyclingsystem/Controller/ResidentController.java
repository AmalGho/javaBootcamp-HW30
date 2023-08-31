package com.example.recyclingsystem.Controller;

import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.DTO.CompanyDTO;
import com.example.recyclingsystem.DTO.ResidentDTO;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.ResidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resident")
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping("/getAll")
    public ResponseEntity getAllResident() {
        return ResponseEntity.status(HttpStatus.OK).body(residentService.getAllResidents());
    }

    @PostMapping("/addProfile")
    public ResponseEntity addProfile(@AuthenticationPrincipal User user, @RequestBody @Valid ResidentDTO residentDTO) {
        residentService.addProfile(user.getId(), residentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("your profile completed"));
    }

    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@AuthenticationPrincipal User user, @RequestBody @Valid ResidentDTO residentDTO) {
        residentService.updateEmail(user.getId(), residentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("email updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user) {
        residentService.deleteAccount(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account deleted successfully"));
    }

    @PutMapping("/wallet")
    public ResponseEntity addMoneyToWallet(@AuthenticationPrincipal User user) {
        residentService.addMoneyToWallet(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("money added to your wallet"));
    }

    @PutMapping("/prize")
    public ResponseEntity getPrize(@AuthenticationPrincipal User user) {
        residentService.getPrize(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("congrats, you get prize"));
    }
}
