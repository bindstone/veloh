package com.bindstone.veloh.mybatis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;

public class ConverterTest {

    @Test
    void runTest() throws ParseException {

        var bytes = WKBReader.hexToBytes("0101000020E6100000645DDC4603781840EC51B81E85CB4840");
        WKBReader reader = new WKBReader(new GeometryFactory(new PrecisionModel(), 4326));
        var point = (Point) reader.read(bytes);
        Assertions.assertNotNull(point);
    }
}