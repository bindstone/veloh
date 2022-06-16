package com.bindstone.veloh.neo4j.controller;

import com.bindstone.veloh.neo4j.entity.Station;
import com.bindstone.veloh.neo4j.repository.dao.StationDistanceDao;
import com.bindstone.veloh.neo4j.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public Flux<Station> findAll() {
        return stationService.findAll();
    }

    @GetMapping(value = "/next")
    // http://127.0.0.1:8080/station/next?lon=0.0&lat=0.0
    public Flux<StationDistanceDao> findNext(@RequestParam Double lon, @RequestParam Double lat) {
        return stationService.findNext(lon, lat);
    }

    @GetMapping(value = "/init")
    public Mono<Void> init() {
        return stationService.init();
    }
}
