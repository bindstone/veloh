package com.bindstone.veloh.rx.service;

import com.bindstone.veloh.rx.entity.Station;
import com.bindstone.veloh.rx.repository.dao.StationDistanceDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationService {

    Flux<Station> findAll();

    Mono<Void> init();

    Flux<StationDistanceDao> findNext(Double lat, Double lng);
}
