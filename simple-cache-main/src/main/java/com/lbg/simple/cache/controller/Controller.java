package com.lbg.simple.cache.controller;

import com.lbg.simple.cache.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    private final CacheService cacheService;

    public Controller(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/getData")
    public ResponseEntity<Map<String, String>> getHandler() {
        /*
         * Image this is the entry point for processing the requests from from3.
         * For simplicity and debugging:
         * 1. getMapping is used.
         * 2. cache data is returned.
         */
        return ResponseEntity.ok(cacheService.getData());
    }
}
