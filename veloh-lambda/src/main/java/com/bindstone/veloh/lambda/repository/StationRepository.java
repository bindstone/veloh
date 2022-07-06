package com.bindstone.veloh.lambda.repository;

import com.bindstone.veloh.lambda.entity.Station;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface StationRepository extends R2dbcRepository<Station, Long> {
}
