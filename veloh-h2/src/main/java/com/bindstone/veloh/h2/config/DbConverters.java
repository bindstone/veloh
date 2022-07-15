package com.bindstone.veloh.h2.config;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DbConverters implements AttributeConverter<Point, byte[]> {


    @Override
    public byte[] convertToDatabaseColumn(Point point) {
        WKBWriter writer = new WKBWriter();
        return writer.write(point);
    }

    @Override
    public Point convertToEntityAttribute(byte[] b) {
        try {
            WKBReader reader = new WKBReader(new GeometryFactory(new PrecisionModel(), 4326));
            return (Point) reader.read(b);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
