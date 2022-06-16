CREATE TABLE IF NOT EXISTS STATION
(
    ID           BIGINT,
    STATION_NAME VARCHAR(500) NOT NULL,
    ADDRESS      VARCHAR(500) NOT NULL,
    LOCATION     geography(POINT, 4326)
);
---
