package com.coffee.shop.controller;

import com.coffee.shop.model.ClosestShop;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/location")
public class LocationManagementController {

    @Operation(summary = "Find the coffee shops closest to the customer's location", description = "", tags = {"Location Management"})
    @RequestMapping(value = "/closest",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<ClosestShop>> shopsClosestGet(@NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @RequestParam(value = "latitude", required = true) Double latitude, @NotNull @Parameter(in = ParameterIn.QUERY, description = "", required = true, schema = @Schema()) @Valid @RequestParam(value = "longitude", required = true) Double longitude) {
        return null;
    }

}

