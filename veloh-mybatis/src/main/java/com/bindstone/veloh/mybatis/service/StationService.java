package com.bindstone.veloh.mybatis.service;

import com.bindstone.veloh.mybatis.entity.Station;
import com.bindstone.veloh.mybatis.repository.dao.StationDistanceDao;

import java.util.List;

public interface StationService {

    List<Station> findAll();

    void init();

    List<StationDistanceDao> findNext(Double lon, Double lat);

    Station findById(int id);
}