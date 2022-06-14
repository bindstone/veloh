package com.bindstone.veloh.classic.service;

import com.bindstone.veloh.classic.entity.Station;
import com.bindstone.veloh.classic.repository.StationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bindstone.veloh.classic.repository.dao.StationDistanceDao;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

private final StationRepository stationRepository;
private final EntityManager entityManager;

    public StationServiceImpl(StationRepository stationRepository, EntityManager entityManager) {
        this.stationRepository = stationRepository;
        this.entityManager = entityManager;
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
    public List<StationDistanceDao> findNext(Double lat, Double lng) {
        List<Tuple> result = this.entityManager
                .createNativeQuery("SELECT * FROM fct_closest_stations(:lat, :lng, :maxStations)", Tuple.class)
                .setParameter("lat",lat)
                .setParameter("lng",lng)
                .setParameter("maxStations",10)
                .getResultList();

        return result.stream().map(tuple -> new StationDistanceDao(
                    (String) tuple.get("name"),
                    (String) tuple.get("address"),
                    (Double) tuple.get("distance")
                    )).collect(Collectors.toList());
    }

    private List<Station> loadJson() {
        List<Station> rtn = new ArrayList<>();
        try {
            var json = new ObjectMapper().readValue(new File("src/main/resources/data/luxembourg.json"), JsonNode.class);
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
