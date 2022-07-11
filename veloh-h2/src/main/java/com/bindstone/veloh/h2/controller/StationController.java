package com.bindstone.veloh.h2.controller;

import com.bindstone.veloh.h2.entity.Station;
import com.bindstone.veloh.h2.service.StationService;
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

    @GetMapping(value = "/init")
    public ResponseEntity init() {
        stationService.init();
        return ResponseEntity.ok().build();
    }
}
