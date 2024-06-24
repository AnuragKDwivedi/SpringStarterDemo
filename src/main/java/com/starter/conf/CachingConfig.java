package com.starter.conf;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .initialCapacity(100) // Initial cache size
                .maximumSize(1000) // Maximum cache size
                .expireAfterWrite(24, TimeUnit.HOURS) // Cache expiration time
                .recordStats();
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
    	 CaffeineCacheManager cacheManager = new CaffeineCacheManager("employee"); // Define your cache names here
         cacheManager.setCaffeine(caffeineConfig());
        return cacheManager;
    }
}
