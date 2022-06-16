package com.bindstone.veloh.neo4j.repository;

import com.bindstone.veloh.neo4j.entity.Station;
import com.bindstone.veloh.neo4j.repository.dao.StationDistanceDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class StationDistanceRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public StationDistanceRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        reactiveMongoTemplate.indexOps(Station.class).ensureIndex(new GeospatialIndex("location").typed(GeoSpatialIndexType.GEO_2DSPHERE)).block();
    }

    public Flux<StationDistanceDao> findNext(Double lon, Double lat) {
        var near = Aggregation.geoNear(NearQuery.near(new GeoJsonPoint(lon, lat)), "distance");
        var sort = Aggregation.sort(Sort.Direction.ASC, "distance");
        var limit = Aggregation.limit(5);
        var projection = Aggregation.project("name", "address", "distance").andExpression("_id").as("id");
        var agg = Aggregation.newAggregation(Station.class, near, sort, projection, limit);
        return reactiveMongoTemplate.aggregate(agg, StationDistanceDao.class);
    }
}
