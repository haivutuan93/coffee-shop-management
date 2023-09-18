package com.coffee.common.utils;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    Long userId;
    String username;
    String email;
    String phone;
    String resourceId;
    List<String> roles;
}
