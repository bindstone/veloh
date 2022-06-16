package com.bindstone.veloh.neo4j.repository;

import com.bindstone.veloh.neo4j.entity.Station;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;

public interface StationRepository extends ReactiveNeo4jRepository<Station, Long> {
}
