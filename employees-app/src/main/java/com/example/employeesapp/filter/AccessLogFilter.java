package com.example.employeesapp.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class AccessLogFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger("ACCESS_LOG_FILE_LOGGER");

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper;

        if (httpServletRequest instanceof ContentCachingRequestWrapper) {
            requestWrapper = (ContentCachingRequestWrapper) httpServletRequest;
        } else {
            requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        }

        ContentCachingResponseWrapper responseWrapper;

        if (httpServletResponse instanceof ContentCachingResponseWrapper) {
            responseWrapper = (ContentCachingResponseWrapper) httpServletResponse;
        } else {
            responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
        }

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            // ---- リクエストログの出力 ----
            String uri = requestWrapper.getRequestURI();

            String queryString = requestWrapper.getQueryString();
            if (StringUtils.isNotEmpty(queryString)) {
                uri += "?" + queryString;
            }

            String method = requestWrapper.getMethod();

            List<String> requestHeaders = new ArrayList<>();
            for (String headerName : Collections.list(requestWrapper.getHeaderNames())) {
                for (String headerValue : Collections.list(requestWrapper.getHeaders(headerName))) {
                    requestHeaders.add(headerName + ": " + headerValue);
                }
            }

            // `ContentCachingRequestWrapper.getContentAsByteArray`メソッドは、
            // `FilterChain.doFilter`メソッド実行後に、実行しないと空が返却される。
            String requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

            logger.info("[Request] URI->{}\\nMethod->{}\\nHeaders->{}\\nBody->{}",
                    uri, method, requestHeaders, requestBody);

            // ---- レスポンスログの出力 ----
            int status = responseWrapper.getStatus();
            String reasonPhrase = HttpStatus.valueOf(status).getReasonPhrase();

            List<String> responseHeaders = new ArrayList<>();
            for (String headerName : responseWrapper.getHeaderNames()) {
                for (String headerValue : responseWrapper.getHeaders(headerName)) {
                    responseHeaders.add(headerName + ": " + headerValue);
                }
            }

            String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

            logger.info("[Response] Status->{}\\nReason Phrase->{}\\nHeaders->{}\\nBody->{}",
                    status, reasonPhrase, responseHeaders, responseBody);

            responseWrapper.copyBodyToResponse();
        }
    }
}
