package com.coffee.shop.service;

import com.coffee.shop.model.CustomerRegisterRequest;
import com.coffee.shop.model.LoginRequest;
import com.coffee.shop.model.LoginResponse;
import com.coffee.shop.model.RegisterResponse;

public interface UserService {
    RegisterResponse registerCustomer(CustomerRegisterRequest request);
    LoginResponse login(String username, String password);
}

