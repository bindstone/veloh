package com.bindstone.veloh.h2.service;

import com.bindstone.veloh.h2.entity.Station;

import java.util.List;

public interface StationService {

    List<Station> findAll();

    void init();

}
