package com.coffee.shop.service.impl;

import com.coffee.common.utils.JwtUtils;
import com.coffee.common.utils.UserInfo;
import com.coffee.shop.entity.User;
import com.coffee.shop.model.CustomerRegisterRequest;
import com.coffee.shop.model.LoginRequest;
import com.coffee.shop.model.LoginResponse;
import com.coffee.shop.model.RegisterResponse;
import com.coffee.shop.repository.UserRepository;
import com.coffee.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public RegisterResponse registerCustomer(CustomerRegisterRequest request) {
        User user = new User();
        user.setMobileNumber(request.getMobileNumber());
        user.setUserName(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setRegularAddress(request.getAddress());
        var registeredUser = userRepository.save(user);

        return RegisterResponse.builder()
                .userId(Math.toIntExact(registeredUser.getId()))
                .mobileNumber(registeredUser.getMobileNumber())
                .name(registeredUser.getUserName())
                .address(registeredUser.getRegularAddress())
                .build();
    }

    @Override
    public LoginResponse login(String username, String password) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (BCrypt.checkpw(password, user.getPassword())) {

            var userInfo = new UserInfo();
            userInfo.setUserId(user.getId());
            userInfo.setUsername(user.getUserName());
            userInfo.setPhone(user.getMobileNumber());

            String accessToken = JwtUtils.generateToken(userInfo);

            LoginResponse response = LoginResponse.builder()
                    .accessToken(accessToken)
                    .expiresIn(new BigDecimal(3600))
                    .tokenType("Bearer")
                    .build();

            return response;
        } else {
            throw  new RuntimeException("Password incorrect");
        }
    }
}

