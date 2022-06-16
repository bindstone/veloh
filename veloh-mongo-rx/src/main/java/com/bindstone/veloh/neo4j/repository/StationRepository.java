package com.bindstone.veloh.neo4j.repository;

import com.bindstone.veloh.neo4j.entity.Station;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StationRepository extends ReactiveMongoRepository<Station, Long> {
}
