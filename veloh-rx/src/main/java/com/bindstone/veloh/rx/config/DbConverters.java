package com.bindstone.veloh.rx.config;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import com.vividsolutions.jts.io.WKBWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

@Configuration
public class DbConverters {

    @Bean
    public R2dbcCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new GeometryConverterFrom());
        converters.add(new GeometryConverterTo());
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters);
    }

    public class GeometryConverterFrom implements Converter<String, Point> {

        @Override
        public Point convert(String source) {
            try {
                // Text reader vs Byte Reader
                //WKTReader reader = new WKTReader(new GeometryFactory(new PrecisionModel(), 4326));
                WKBReader reader = new WKBReader(new GeometryFactory(new PrecisionModel(), 4326));
                //return (Point) reader.read( "POINT(-123 34)" );
                return (Point)reader.read(HexFormat.of().parseHex(source));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class GeometryConverterTo implements Converter<Point, Object> {
        @Override
        public byte[] convert(Point source) {
            WKBWriter writer = new WKBWriter();
            return writer.write(source);
        }
    }

}
