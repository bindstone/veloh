package com.bindstone.veloh.rx.service;

import com.bindstone.veloh.rx.entity.Station;
import com.bindstone.veloh.rx.repository.StationRepository;
import com.bindstone.veloh.rx.repository.dao.StationDistanceDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
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
    public Flux<StationDistanceDao> findNext(Double lat, Double lng) {
        return Flux.empty();
    }

    private List<Station> loadJson() {
        List<Station> rtn = new ArrayList<>();
        try {
            var json = new ObjectMapper().readValue(new File("./data/luxembourg.json"), JsonNode.class);
            if(json.isArray()) {
                json.forEach(new Consumer<JsonNode>() {
                    @Override
                    public void accept(JsonNode jsonNode) {
                        var station = new Station();
                        station.setId(jsonNode.get("number").asLong());
                        station.setAddress(jsonNode.get("address").asText());
                        station.setName(jsonNode.get("name").asText());
                        var lat = jsonNode.get("latitude").asDouble();
                        var lng = jsonNode.get("longitude").asDouble();
                        GeometryFactory geomFactory = new GeometryFactory(new PrecisionModel(), 4326);
                        station.setLocation(geomFactory.createPoint(new Coordinate(lat, lng)));
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
