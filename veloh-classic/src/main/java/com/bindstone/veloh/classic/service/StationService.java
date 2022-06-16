package com.bindstone.veloh.classic.service;

import com.bindstone.veloh.classic.entity.Station;
import com.bindstone.veloh.classic.repository.dao.StationDistanceDao;

import java.util.List;

public interface StationService {

    List<Station> findAll();

    void init();

    List<StationDistanceDao> findNext(Double lat, Double lon);
}
