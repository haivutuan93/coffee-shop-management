package com.coffee.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestResponseLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            ContentCachingRequestWrapper requestWrapper = requestWrapper(request);
            ContentCachingResponseWrapper responseWrapper = responseWrapper(response);

            chain.doFilter(requestWrapper, responseWrapper);

            logRequest(requestWrapper);
            logResponse(responseWrapper);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        StringBuilder paramsValue = new StringBuilder();
        paramsValue.append(new String(request.getContentAsByteArray()));
        log.info("Request: URL: {}, method: {}, contentType: {}, value: {}", request.getRequestURL(), request.getMethod(), request.getContentType(), paramsValue);
    }

    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
        StringBuilder values = new StringBuilder();
        values.append(new String(response.getContentAsByteArray()));
        log.info("Response: status: {}, value: {}", response.getStatus(), values);
        response.copyBodyToResponse();
    }

    private ContentCachingRequestWrapper requestWrapper(ServletRequest request) {
        return new ContentCachingRequestWrapper((HttpServletRequest) request);
    }

    private ContentCachingResponseWrapper responseWrapper(ServletResponse response) {
        return new ContentCachingResponseWrapper((HttpServletResponse) response);
    }

}
