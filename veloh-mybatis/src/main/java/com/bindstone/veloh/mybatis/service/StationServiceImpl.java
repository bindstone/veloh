package com.bindstone.veloh.mybatis.service;

import com.bindstone.veloh.mybatis.entity.Station;
import com.bindstone.veloh.mybatis.repository.StationRepository;
import com.bindstone.veloh.mybatis.repository.dao.StationDistanceDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    @Transactional
    public void init() {
        var newStations = loadJson();
        stationRepository.deleteAll();
        stationRepository.saveAll(newStations);
    }

    @Override
    public List<StationDistanceDao> findNext(Double lon, Double lat) {
        return stationRepository.findNext(lon, lat, 5);
    }

    @Override
    public Station findById(int id) {
        return stationRepository.findById(id);
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
                        GeometryFactory geomFactory = new GeometryFactory(new PrecisionModel(), 4326);
                        station.setLocation(geomFactory.createPoint(new Coordinate(lon, lat)));
                        rtn.add(station);
                    }
                });
            }
            System.out.println("Data imported");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return rtn;
    }
}