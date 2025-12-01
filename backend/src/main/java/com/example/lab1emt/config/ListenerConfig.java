package com.example.lab1emt.config;

import com.example.lab1emt.listeners.AuthorEntityListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

@Configuration
public class ListenerConfig {

    private final JdbcTemplate jdbcTemplate;

    public ListenerConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        AuthorEntityListener.setJdbcTemplate(jdbcTemplate);
    }
}
