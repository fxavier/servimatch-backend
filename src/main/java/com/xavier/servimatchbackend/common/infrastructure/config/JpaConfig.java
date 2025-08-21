package com.xavier.servimatchbackend.common.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.xavier.servimatchbackend")
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaConfig {
}
