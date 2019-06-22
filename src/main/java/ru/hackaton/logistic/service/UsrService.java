package ru.hackaton.logistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.logistic.repository.UsrRepository;
import ru.hackaton.logistic.request.LoginRequest;

@Service
@RequiredArgsConstructor
public class UsrService {

    private final UsrRepository usrRepository;

    public Long login(LoginRequest loginRequest){
        try{
            return usrRepository.findTopByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword()).getId();
        } catch (Exception e){
            return -1L;
        }
    }

}
