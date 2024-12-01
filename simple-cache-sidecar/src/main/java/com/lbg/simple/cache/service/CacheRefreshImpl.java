package com.lbg.simple.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CacheRefreshImpl implements CacheRefresh {

    private static final Logger LOG = LoggerFactory.getLogger(CacheRefreshImpl.class);

    @Scheduled(fixedRateString = "10000")
    public void refresh() {

        final WebClient client = WebClient.create("http://localhost:8088/actuator/caches");

        final String responseMessage = client.delete()
                .uri(uriBuilder -> uriBuilder.path("/{cacheName}").build("dataCache"))
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return Mono.just("Cache evicted.");
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                })
                .block();

        LOG.info("The response body: {}", responseMessage);
    }
}
