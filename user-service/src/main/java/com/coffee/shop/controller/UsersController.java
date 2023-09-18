package com.coffee.shop.controller;

import com.coffee.shop.model.CustomerRegisterRequest;
import com.coffee.shop.model.DeviceRegisterRequest;
import com.coffee.shop.model.DeviceRegisterResponse;
import com.coffee.shop.model.LoginRequest;
import com.coffee.shop.model.RegisterResponse;
import com.coffee.shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @Operation(summary = "Customer registration", description = "", tags = {"User Actions"})
    @RequestMapping(value = "/user/customer/register",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<RegisterResponse> registerCustomer(@Valid @RequestBody CustomerRegisterRequest request) {
        var response = userService.registerCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "Register a new device for notifications", description = "", tags = {"User Actions"})
    @RequestMapping(value = "/user/device",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<DeviceRegisterResponse> usersDevicesPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody DeviceRegisterRequest body) {
        return null;
    }


    @Operation(summary = "User login", description = "", tags = {"User Actions"})
    @RequestMapping(value = "/user/login",
            consumes = "*/*",
            method = RequestMethod.POST)
    ResponseEntity<?> usersLoginPost(@RequestParam String username, @RequestParam String password) {
        try {
            var response = userService.login(username, password);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "User logout", description = "", tags = {"User Actions"})
    @RequestMapping(value = "/user/logout",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<Void> usersLogoutPost() {
        return null;
    }

}

