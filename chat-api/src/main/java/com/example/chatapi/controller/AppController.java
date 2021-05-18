package com.example.chatapi.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestController
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/getLatestMessages")
    public String getLatestMessages(@RequestParam String requestId, @RequestParam String threadId,
                                    @RequestParam String latestMessageId) throws IOException {

        logger.info("[Called] getLatestMessages({}, {}, {})", requestId, threadId, latestMessageId);

        String response;
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("getLatestMessages-response.json")) {
            response = IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        }

        return response;
    }

    @GetMapping("/getOldestMessages")
    public String getOldestMessages(@RequestParam String requestId, @RequestParam String threadId,
                                    @RequestParam String oldestMessageId) throws IOException {

        logger.info("[Called] getOldestMessages({}, {}, {})", requestId, threadId, oldestMessageId);

        String response;
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("getOldestMessages-response.json")) {
            response = IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
        }

        return response;
    }
}
