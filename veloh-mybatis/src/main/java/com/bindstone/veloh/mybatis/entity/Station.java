package com.bindstone.veloh.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {

    private Long id;

    private String name;

    private String address;

    private Point location;

}