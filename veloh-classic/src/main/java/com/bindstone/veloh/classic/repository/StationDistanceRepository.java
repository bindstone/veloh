package com.bindstone.veloh.classic.repository;

import com.bindstone.veloh.classic.repository.dao.StationDistanceDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StationDistanceRepository {

    private final EntityManager entityManager;

    public StationDistanceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<StationDistanceDao> findNext(Double lon, Double lat) {
        List<Tuple> result = this.entityManager
                .createNativeQuery("SELECT * FROM fct_closest_stations(:lon, :lat, :maxStations)", Tuple.class)
                .setParameter("lon", lon)
                .setParameter("lat", lat)
                .setParameter("maxStations", 5)
                .getResultList();

        return result.stream().map(tuple -> new StationDistanceDao(
                ((BigInteger) tuple.get("id")).longValue(),
                (String) tuple.get("name"),
                (String) tuple.get("address"),
                (Double) tuple.get("distance")
        )).collect(Collectors.toList());
    }
}
