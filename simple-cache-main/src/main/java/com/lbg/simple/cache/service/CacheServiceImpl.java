package com.lbg.simple.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CacheServiceImpl implements CacheService {

    private static final String CACHE_NAME = "dataCache";

    private static final Logger LOG = LoggerFactory.getLogger(CacheServiceImpl.class);

    private final DataService dataService;

    public CacheServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Cacheable(CACHE_NAME)
    public Map<String, String> getData() {
        return dataService.getDataFromSource();
    }

    /*@CacheEvict(value = CACHE_NAME, allEntries = true)
    @Scheduled(fixedRateString = "10000")
    public void evictData() {
        LOG.info("Evicting data from cache.");
    }*/
}
