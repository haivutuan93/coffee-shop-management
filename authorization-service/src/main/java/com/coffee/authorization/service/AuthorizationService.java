package com.coffee.authorization.service;

import org.springframework.security.core.Authentication;

public interface AuthorizationService {
    boolean hasPermission(Authentication authentication, String apiUrl, String apiMethod);

    void clearCache();
}
