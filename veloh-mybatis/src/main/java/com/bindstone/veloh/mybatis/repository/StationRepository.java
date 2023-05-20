package com.bindstone.veloh.mybatis.repository;

import com.bindstone.veloh.mybatis.entity.Station;
import com.bindstone.veloh.mybatis.repository.dao.StationDistanceDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StationRepository {

    List<StationDistanceDao> findNext(@Param("lon") Double lon, @Param("lat") Double lat, @Param("maxStations") int maxStations);

    List<Station> findAll();

    void deleteAll();

    void save(Station stations);

    void saveAll(List<Station> stations);

    Station findById(@Param("id") int id);
}