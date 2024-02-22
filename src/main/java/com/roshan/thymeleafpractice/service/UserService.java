package com.roshan.thymeleafpractice.service;

import com.roshan.thymeleafpractice.dto.login.UserLoginRequestDto;
import com.roshan.thymeleafpractice.dto.login.UserLoginResponseDto;
import com.roshan.thymeleafpractice.dto.register.UserRegistrationRequestDto;
import com.roshan.thymeleafpractice.entity.User;

public interface UserService {
    UserLoginResponseDto userLogin(String identifier, String password, UserLoginRequestDto userLoginRequestDto);

    User createUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
