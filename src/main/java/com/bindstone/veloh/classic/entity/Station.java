package com.bindstone.veloh.classic.entity;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="station")
@Table(name="station")
public class Station {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "station_name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(columnDefinition = "geography", name="location")
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

