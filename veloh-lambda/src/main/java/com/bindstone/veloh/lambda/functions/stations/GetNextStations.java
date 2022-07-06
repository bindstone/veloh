package com.bindstone.veloh.lambda.functions.stations;

import com.bindstone.veloh.lambda.entity.Position;
import com.bindstone.veloh.lambda.repository.dao.StationDistanceDao;
import com.bindstone.veloh.lambda.service.StationService;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class GetNextStations implements Function<Position, Flux<StationDistanceDao>> {

    private final StationService stationService;

    public GetNextStations(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public Flux<StationDistanceDao> apply(Position position) {
        return stationService.findNext(position.getLon(), position.getLat());
    }
}
