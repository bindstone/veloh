package com.bindstone.veloh.lambda.service;

import com.bindstone.veloh.lambda.entity.Station;
import com.bindstone.veloh.lambda.repository.dao.StationDistanceDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationService {

    Flux<Station> findAll();

    Mono<Void> init();

    Flux<StationDistanceDao> findNext(Double lon, Double lat);
}
