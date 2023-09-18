package com.coffee.common.utils;

import com.coffee.common.exception.UnauthorizedException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY = "coffeeShopSecretKey";

    public static UserInfo getUserInfo(){
        String token = getBearerTokenHeader();

        String payload = token.split("\\.")[1];

        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String payloadStr = new String(decodedBytes, StandardCharsets.UTF_8);

        JsonObject jsonObject = new Gson().fromJson(payloadStr, JsonObject.class);

        UserInfo userInfo = new UserInfo();
        try {
            userInfo.setUserId(Long.valueOf(jsonObject.get("sub") != null ? jsonObject.get("sub").getAsString() : null));
            userInfo.setUsername(jsonObject.get("name") != null ? jsonObject.get("name").getAsString() : null);
            userInfo.setPhone(jsonObject.get("phone") != null ? jsonObject.get("phone").getAsString() : null);
            userInfo.setRoles(jsonObject.getAsJsonObject().get("roles") != null ? new Gson().fromJson(jsonObject.getAsJsonObject().get("roles").getAsJsonArray(), ArrayList.class) : null);
        } catch (Exception e){
            e.printStackTrace();
        }

        return userInfo;
    }

    public static String getBearerTokenHeader() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").replaceAll("Bearer ", "");
        } catch (Exception e){
            throw new UnauthorizedException(ErrorMessages.AUTH_REQUIRED);
        }
    }

    public static String generateToken(UserInfo user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getUserId().toString());
        claims.put("name", user.getUsername());
        claims.put("phone", user.getPhone());
        claims.put("roles", user.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
