package com.bindstone.veloh.rx.repository;

import com.bindstone.veloh.rx.repository.dao.StationDistanceDao;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface StationDistanceRepository extends R2dbcRepository<StationDistanceDao, Long> {

    @Query("SELECT * FROM fct_closest_stations(:lon, :lat, 5)")
    Flux<StationDistanceDao> findNext(@Param("lon") Double lon, @Param("lat") Double lat);
}
