package com.example.recyclingsystem.Service;

import com.example.recyclingsystem.Api.ApiException;
import com.example.recyclingsystem.DTO.ResidentDTO;
import com.example.recyclingsystem.Model.Request;
import com.example.recyclingsystem.Model.Resident;
import com.example.recyclingsystem.Model.User;
import com.example.recyclingsystem.Repository.AuthRepository;
import com.example.recyclingsystem.Repository.RequestRepository;
import com.example.recyclingsystem.Repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final AuthRepository authRepository;

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }


    public void addProfile(Integer resident_id, ResidentDTO residentDTO) {
        User user = authRepository.findUserById(resident_id);

        Resident resident = new Resident(null, residentDTO.getEmail(), 0, 0.0, user, null);
        residentRepository.save(resident);
    }


    public void updateEmail(Integer resident_id, ResidentDTO residentDTO) {
        Resident oldResident = residentRepository.findResidentById(resident_id);

        oldResident.setEmail(residentDTO.getEmail());
        residentRepository.save(oldResident);
    }

    public void deleteAccount(Integer user_id) {
        User user = authRepository.findUserById(user_id);

        authRepository.delete(user);
        residentRepository.delete(user.getResident());

    }

    public void addMoneyToWallet(Integer user_id) {
        User user = authRepository.findUserById(user_id);

        if (user.getResident().getPoints() == 20) {
            user.getResident().setWallet(user.getResident().getWallet() + 100);
            user.getResident().setPoints(0);
            authRepository.save(user);
        }else throw new ApiException("your points still less than 20");
    }

    public void getPrize(Integer user_id) {
        User user = authRepository.findUserById(user_id);

        if (user.getResident().getRequests().size() >= 10) {
            user.getResident().setWallet(user.getResident().getWallet() + 50);
        } else if (user.getResident().getRequests().size() == 5) {
            user.getResident().setPoints(user.getResident().getPoints() + 5);
        }else throw new ApiException("sorry, there is no prize for you yet :(");
    }


}
