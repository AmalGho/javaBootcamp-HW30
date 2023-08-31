package com.example.recyclingsystem.Controller;


import com.example.recyclingsystem.Api.ApiResponse;
import com.example.recyclingsystem.DTO.CompanyDTO;
import com.example.recyclingsystem.Model.Company;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/getAll")
    public ResponseEntity getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
    }

    @PostMapping("/addProfile")
    public ResponseEntity addProfile(@AuthenticationPrincipal User user, @RequestBody @Valid CompanyDTO companyDTO) {
        companyService.addProfile(user.getId(), companyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("your profile completed"));
    }


    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@AuthenticationPrincipal User user, @RequestBody @Valid Company company) {
        companyService.updateEmail(user.getId(), company);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("email updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user) {
        companyService.deleteAccount(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("account deleted successfully"));
    }
}
