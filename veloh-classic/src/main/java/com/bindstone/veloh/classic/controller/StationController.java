package com.bindstone.veloh.classic.controller;

import com.bindstone.veloh.classic.entity.Station;
import com.bindstone.veloh.classic.repository.dao.StationDistanceDao;
import com.bindstone.veloh.classic.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public ResponseEntity<List<Station>> findAll() {
        return ResponseEntity.ok(stationService.findAll());
    }

    @GetMapping(value = "/next")
    // http://127.0.0.1:8080/station/next?lon=0.0&lat=0.0
    public ResponseEntity<List<StationDistanceDao>> findNext(@RequestParam Double lon, @RequestParam Double lat) {
        return ResponseEntity.ok(stationService.findNext(lon, lat));
    }

    @GetMapping(value = "/init")
    public ResponseEntity init() {
        stationService.init();
        return ResponseEntity.ok().build();
    }
}
