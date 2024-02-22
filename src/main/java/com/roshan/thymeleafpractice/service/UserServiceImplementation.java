package com.roshan.thymeleafpractice.service;

import com.roshan.thymeleafpractice.dto.login.UserLoginRequestDto;
import com.roshan.thymeleafpractice.dto.login.UserLoginResponseDto;
import com.roshan.thymeleafpractice.dto.register.UserRegistrationRequestDto;
import com.roshan.thymeleafpractice.repository.UserRepository;


import org.springframework.stereotype.Service;
import com.roshan.thymeleafpractice.entity.User;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserLoginResponseDto userLogin(String identifier, String password, UserLoginRequestDto userLoginRequestDto) {
        boolean loginSuccess = userRepository.findUserByIdentifierAndPassword(identifier, password).isPresent();
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        if (loginSuccess) {
            userLoginResponseDto.setUsername(userRepository.findByIdentifier(identifier));
            return userLoginResponseDto;
        }
        userLoginResponseDto.setUsername("");
        return userLoginResponseDto;
    }

    @Override
    public User createUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = new User(userRegistrationRequestDto.getUsername(), userRegistrationRequestDto.getEmail(), userRegistrationRequestDto.getPassword());
        return userRepository.save(user);
    }

}
