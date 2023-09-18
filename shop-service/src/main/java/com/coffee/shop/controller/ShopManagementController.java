package com.coffee.shop.controller;

import com.coffee.shop.model.ProductUpdateRequest;
import com.coffee.shop.model.QueueConfigRequest;
import com.coffee.shop.model.ShopProduct;
import com.coffee.shop.model.ShopRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/shop")
public class ShopManagementController {
    @Operation(summary = "Create a new shop", description = "", tags = {"Shop Management"})
    @RequestMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<ShopRequest> shopsPost() {
        return null;
    }

    @Operation(summary = "Get the menu products of a shop", description = "", tags = {"Shop Management"})
    @RequestMapping(value = "/{shopId}/product",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<ShopProduct>> shopsShopIdProductGet(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("shopId") Integer shopId) {
        return null;
    }

    @Operation(summary = "Update the coffee menu and pricing", description = "", tags = {"Shop Management"})
    @RequestMapping(value = "/{shopId}/product/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    ResponseEntity<Void> shopsShopIdProductUpdatePut(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("shopId") Integer shopId, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody List<ProductUpdateRequest> body) {
        return null;
    }

    @Operation(summary = "Update shop details", description = "", tags = {"Shop Management"})
    @RequestMapping(value = "/{shopId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    ResponseEntity<Void> shopsShopIdPut(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("shopId") Integer shopId, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody ShopRequest body) {
        return null;
    }

    @Operation(summary = "Configure the number of queues and the maximum size of each queue for a shop", description = "", tags = {"Shop Management"})
    @RequestMapping(value = "/{shopId}/queues/config",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    ResponseEntity<Void> shopsShopIdQueuesConfigPut(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("shopId") Integer shopId, @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody QueueConfigRequest body) {
        return null;
    }

}

