package com.paolo.springboot.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("department", new Feature(true));
        featureMap.put("user", new Feature(false));
        featureMap.put("authentication", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> getAllFeatures() {
        return featureMap;
    }

    @ReadOperation
    public Feature getSpecificFeature(@Selector String name) {
        return featureMap.get(name);
    }

    @WriteOperation
    public Feature activateFeature(@Selector String name, @RequestBody boolean isEnabled) {
        featureMap.replace(name, new Feature(isEnabled));
        return featureMap.get(name);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Feature {
        private boolean isEnabled;
    }
}
