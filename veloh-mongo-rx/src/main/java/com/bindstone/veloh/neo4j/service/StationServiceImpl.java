package com.bindstone.veloh.neo4j.service;

import com.bindstone.veloh.neo4j.entity.Station;
import com.bindstone.veloh.neo4j.repository.StationDistanceRepository;
import com.bindstone.veloh.neo4j.repository.StationRepository;
import com.bindstone.veloh.neo4j.repository.dao.StationDistanceDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final StationDistanceRepository stationDistanceRepository;

    public StationServiceImpl(StationRepository stationRepository, StationDistanceRepository stationDistanceRepository) {
        this.stationRepository = stationRepository;
        this.stationDistanceRepository = stationDistanceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    @Transactional
    public Mono<Void> init() {
        var newStations = loadJson();
        return stationRepository.deleteAll()
                .then(stationRepository.saveAll(newStations).then());
    }

    @Override
    public Flux<StationDistanceDao> findNext(Double lon, Double lat) {
        return stationDistanceRepository.findNext(lon, lat);
    }

    private List<Station> loadJson() {
        List<Station> rtn = new ArrayList<>();
        try {
            var json = new ObjectMapper().readValue(new File("./data/luxembourg.json"), JsonNode.class);
            if (json.isArray()) {
                json.forEach(new Consumer<JsonNode>() {
                    @Override
                    public void accept(JsonNode jsonNode) {
                        var station = new Station();
                        station.setId(jsonNode.get("number").asLong());
                        station.setAddress(jsonNode.get("address").asText());
                        station.setName(jsonNode.get("name").asText());
                        var lat = jsonNode.get("latitude").asDouble();
                        var lon = jsonNode.get("longitude").asDouble();
                        station.setLocation(new GeoJsonPoint(lon, lat));
                        rtn.add(station);
                    }
                });
            }
            System.out.println("e");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return rtn;
    }
}
