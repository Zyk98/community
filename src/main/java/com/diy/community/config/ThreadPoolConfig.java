package com.diy.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName ThreadPoolConfig.java
 * @Description Spring 线程池  Config
 */

@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
}
