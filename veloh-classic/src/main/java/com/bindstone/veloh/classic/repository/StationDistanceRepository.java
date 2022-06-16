package com.bindstone.veloh.classic.repository;

import com.bindstone.veloh.classic.repository.dao.StationDistanceDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StationDistanceRepository {

    private final EntityManager entityManager;

    public StationDistanceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<StationDistanceDao> findNext(Double lat, Double lon) {
        List<Tuple> result = this.entityManager
                .createNativeQuery("SELECT * FROM fct_closest_stations(:lat, :lon, :maxStations)", Tuple.class)
                .setParameter("lat", lat)
                .setParameter("lon", lon)
                .setParameter("maxStations", 10)
                .getResultList();

        return result.stream().map(tuple -> new StationDistanceDao(
                (Long) tuple.get("id"),
                (String) tuple.get("name"),
                (String) tuple.get("address"),
                (Double) tuple.get("distance")
        )).collect(Collectors.toList());
    }
}
