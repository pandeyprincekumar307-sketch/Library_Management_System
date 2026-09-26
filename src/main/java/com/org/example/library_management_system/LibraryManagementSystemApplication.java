package com.org.example.library_management_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    private static final Logger log = LoggerFactory.getLogger(LibraryManagementSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner cleanupOldConstraints(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                List<String> checkConstraints = jdbcTemplate.query(
                        "SELECT CONSTRAINT_NAME FROM information_schema.TABLE_CONSTRAINTS " +
                                "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'users' AND CONSTRAINT_TYPE = 'CHECK'",
                        (rs, rowNum) -> rs.getString("CONSTRAINT_NAME")
                );

                for (String constraintName : checkConstraints) {
                    try {
                        jdbcTemplate.execute("ALTER TABLE users DROP CHECK " + constraintName);
                        log.info("Successfully dropped legacy check constraint: {}", constraintName);
                    } catch (Exception e) {
                        log.warn("Could not drop constraint {}: {}", constraintName, e.getMessage());
                    }
                }
            } catch (Exception ex) {
                log.warn("Constraint cleanup skipped: {}", ex.getMessage());
            }
        };
    }
}
