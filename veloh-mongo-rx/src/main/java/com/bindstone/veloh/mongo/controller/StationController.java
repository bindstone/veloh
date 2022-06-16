package com.bindstone.veloh.mongo.controller;

import com.bindstone.veloh.mongo.entity.Station;
import com.bindstone.veloh.mongo.repository.dao.StationDistanceDao;
import com.bindstone.veloh.mongo.service.StationService;
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
    // http://127.0.0.1:8080/station/next?lat=0.0&lon=0.0
    public Flux<StationDistanceDao> findNext(@RequestParam Double lat, @RequestParam Double lon) {
        return stationService.findNext(lat, lon);
    }

    @GetMapping(value = "/init")
    public Mono<Void> init() {
        return stationService.init();
    }
}
