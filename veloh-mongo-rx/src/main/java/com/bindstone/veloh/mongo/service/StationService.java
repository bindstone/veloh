package com.bindstone.veloh.mongo.service;

import com.bindstone.veloh.mongo.entity.Station;
import com.bindstone.veloh.mongo.repository.dao.StationDistanceDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationService {

    Flux<Station> findAll();

    Mono<Void> init();

    Flux<StationDistanceDao> findNext(Double lat, Double lon);
}
