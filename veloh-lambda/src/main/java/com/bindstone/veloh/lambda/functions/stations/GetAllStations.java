package com.bindstone.veloh.lambda.functions.stations;

import com.bindstone.veloh.lambda.entity.Station;
import com.bindstone.veloh.lambda.service.StationService;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

public class GetAllStations implements Supplier<Flux<Station>> {

    private final StationService stationService;

    public GetAllStations(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public Flux<Station> get() {
        return stationService.findAll();
    }
}
