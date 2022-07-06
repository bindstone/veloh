package com.bindstone.veloh.lambda.functions.stations;

import com.bindstone.veloh.lambda.entity.Station;
import com.bindstone.veloh.lambda.service.StationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class InitStations implements Supplier<Mono<Void>> {

    private final StationService stationService;

    public InitStations(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public Mono<Void> get() {
        return stationService.init();
    }
}
