
/*
 * Copyright (c) 2023 FPT Software Co. Ltd
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * FPT Software Co. Ltd ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered
 * into with FPT Software Co. Ltd or its subsidiaries.
 */
package com.coffee.shop.config;

import com.coffee.common.exception.BaseExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler extends BaseExceptionHandler {
    public ExceptionHandler() {
    }
}


