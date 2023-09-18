package com.coffee.shop.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * LoginResponse
 */
@Validated
@Data
@Builder
public class LoginResponse   {
  @JsonProperty("access_token")
  private String accessToken = null;

  @JsonProperty("expires_in")
  private BigDecimal expiresIn = null;

  @JsonProperty("refresh_expires_in")
  private BigDecimal refreshExpiresIn = null;

  @JsonProperty("token_type")
  private String tokenType = null;

  @JsonProperty("refresh_token")
  private String refreshToken = null;

}
