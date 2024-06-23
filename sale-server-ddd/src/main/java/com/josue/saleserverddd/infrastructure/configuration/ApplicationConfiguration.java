package com.josue.saleserverddd.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("app")
public class ApplicationConfiguration {

    private List<String> publicUrls;
    private DBApplicationConfiguration database;

    public List<String> getPublicUrls() {
        return publicUrls;
    }

    public void setPublicUrls(List<String> publicUrls) {
        this.publicUrls = publicUrls;
    }

    public DBApplicationConfiguration getDatabase() {
        return database;
    }

    public void setDatabase(DBApplicationConfiguration database) {
        this.database = database;
    }
}
