package com.lbg.simple.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class DataServiceImpl implements DataService {

    private static final Random random = new Random();

    private static final String[] STATUS = {"active", "revoked", "expired", "pending_verification"};

    private static final Logger LOG = LoggerFactory.getLogger(DataServiceImpl.class);

    public Map<String, String> getDataFromSource() {
        LOG.info("The data is retrieved from source.");
        return Map.of(
                "key1", STATUS[random.nextInt(STATUS.length)],
                "key2", STATUS[random.nextInt(STATUS.length)]
        );
    }
}
