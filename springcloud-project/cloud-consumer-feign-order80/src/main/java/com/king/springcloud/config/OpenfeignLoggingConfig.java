package com.king.springcloud.config;

import com.sun.javafx.util.Logging;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenfeignLoggingConfig {

    @Bean
    public Logger.Level getFeignLogger() {
        return Logger.Level.FULL;
    }
}
