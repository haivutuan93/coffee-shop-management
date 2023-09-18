package com.coffee.authorization.controller;

import com.coffee.authorization.service.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/permission")
public class PermissionController {

    private final AuthorizationService authorizationService;

    @Operation(summary = "Check permission can User access Url, Method")
    @GetMapping()
    public ResponseEntity permission(@RequestParam String url, @RequestParam String method) {
        if(authorizationService.hasPermission(SecurityContextHolder.getContext().getAuthentication(), url, method)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
