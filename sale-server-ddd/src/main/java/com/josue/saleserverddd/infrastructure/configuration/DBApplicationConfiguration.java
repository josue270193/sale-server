package com.josue.saleserverddd.infrastructure.configuration;

public class DBApplicationConfiguration {

    private DatabaseConfiguration postgresql;
    private DatabaseConfiguration redis;

    public DatabaseConfiguration getPostgresql() {
        return postgresql;
    }

    public void setPostgresql(DatabaseConfiguration postgresql) {
        this.postgresql = postgresql;
    }

    public DatabaseConfiguration getRedis() {
        return redis;
    }

    public void setRedis(DatabaseConfiguration redis) {
        this.redis = redis;
    }
}
