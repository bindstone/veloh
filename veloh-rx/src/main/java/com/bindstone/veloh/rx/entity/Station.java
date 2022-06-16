package com.bindstone.veloh.rx.entity;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.relational.core.mapping.Column;

public class Station {

    @Column("id")
    private Long id;
    @Column("station_name")
    private String name;
    @Column("address")
    private String address;
    @Column(value = "location")
    private Point location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}

