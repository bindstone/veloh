package com.bindstone.veloh.neo4j.entity;

import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.types.GeographicPoint2d;
import org.springframework.data.annotation.Id;

@Node
public class Station {
    @Id
    private Long id;

    private String name;

    private String address;

    private GeographicPoint2d location;

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

    public GeographicPoint2d getLocation() {
        return location;
    }

    public void setLocation(GeographicPoint2d location) {
        this.location = location;
    }
}

