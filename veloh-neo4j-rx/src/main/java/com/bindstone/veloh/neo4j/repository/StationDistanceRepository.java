package com.bindstone.veloh.neo4j.repository;

import com.bindstone.veloh.neo4j.repository.dao.StationDistanceDao;
import org.neo4j.driver.types.TypeSystem;
import org.neo4j.springframework.data.core.ReactiveNeo4jClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class StationDistanceRepository {

    private final ReactiveNeo4jClient client;

    public StationDistanceRepository(ReactiveNeo4jClient client) {
        this.client = client;
    }

    public Flux<StationDistanceDao> findNext(Double lon, Double lat) {
        return client.query("MATCH (s:Station) RETURN s.id, s.name, s.address, " +
                        "distance(point({x: " + lon + ", y: " + lat + ", srid: 4326}),s.location) as distance " +
                        "ORDER BY distance " +
                        "LIMIT 5")
                .fetchAs(StationDistanceDao.class)
                .mappedBy((TypeSystem t, org.neo4j.driver.Record record) -> {
                    Long id = record.get("s.id").asLong();
                    String name = record.get("s.name").asString();
                    String address = record.get("s.address").asString();
                    Double distance = record.get("distance").asDouble();
                    return new StationDistanceDao(id, name, address, distance);
                })
                .all();
    }
}
