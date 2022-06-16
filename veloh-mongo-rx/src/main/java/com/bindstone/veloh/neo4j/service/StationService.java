package com.bindstone.veloh.neo4j.service;

import com.bindstone.veloh.neo4j.entity.Station;
import com.bindstone.veloh.neo4j.repository.dao.StationDistanceDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationService {

    Flux<Station> findAll();

    Mono<Void> init();

    Flux<StationDistanceDao> findNext(Double lon, Double lat);
}
