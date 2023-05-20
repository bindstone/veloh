package com.bindstone.veloh.mybatis.controller;

import com.bindstone.veloh.mybatis.entity.Station;
import com.bindstone.veloh.mybatis.repository.dao.StationDistanceDao;
import com.bindstone.veloh.mybatis.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Station> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok(stationService.findById(id));
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