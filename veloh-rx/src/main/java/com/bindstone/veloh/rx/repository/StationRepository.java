package com.bindstone.veloh.rx.repository;

import com.bindstone.veloh.rx.entity.Station;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface StationRepository extends R2dbcRepository<Station, Long> {
}
