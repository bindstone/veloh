package com.bindstone.veloh.mongo.repository;

import com.bindstone.veloh.mongo.entity.Station;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StationRepository extends ReactiveMongoRepository<Station, Long> {
}
