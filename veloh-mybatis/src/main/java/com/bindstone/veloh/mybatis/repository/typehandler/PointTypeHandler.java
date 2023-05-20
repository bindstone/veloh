package com.bindstone.veloh.mybatis.repository.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointTypeHandler extends BaseTypeHandler<Point> {

    private static Point deserialize(String value) throws ParseException {
        if (value == null) {
            return null;
        }

        try {
            var bytes = WKBReader.hexToBytes(value);
            WKBReader reader = new WKBReader(new GeometryFactory(new PrecisionModel(), 4326));
            return (Point) reader.read(bytes);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Point parameter, JdbcType jdbcType) throws SQLException {
        byte[] bytes = serialize(parameter);
        ps.setBytes(i, bytes);
    }

    @Override
    public Point getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String bytes = rs.getString(columnName);
        try {
            return deserialize(bytes);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Point getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String bytes = rs.getString(columnIndex);
        try {
            return deserialize(bytes);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Point getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String bytes = cs.getString(columnIndex);
        try {
            return deserialize(bytes);
        } catch (ParseException e) {
            throw new SQLException(e);
        }
    }

    private byte[] serialize(Point point) {
        WKBWriter writer = new WKBWriter();
        return writer.write(point);
    }
}