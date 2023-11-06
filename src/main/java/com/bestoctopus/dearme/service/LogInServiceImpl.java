package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {
    private final UserRepository userRepository;

    @Autowired
    public LogInServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void validateId(String id){
        userRepository.findById(id)
                .ifPresent(throw new );
    }
}
