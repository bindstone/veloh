package com.bindstone.veloh.rx.controller;

import com.bindstone.veloh.rx.entity.Station;
import com.bindstone.veloh.rx.repository.dao.StationDistanceDao;
import com.bindstone.veloh.rx.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
    // http://127.0.0.1:8080/station/next?lat=0.0&lng=0.0
    public Flux<StationDistanceDao> findNext(@RequestParam Double lat, @RequestParam Double lng) {
        return stationService.findNext(lat, lng);
    }

    @GetMapping(value = "/init")
    public Mono<Void> init() {
        return stationService.init();
    }
}
