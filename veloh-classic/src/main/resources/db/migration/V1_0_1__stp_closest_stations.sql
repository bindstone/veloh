create
    or replace function fct_closest_stations(
    lon double precision,
    lat double precision,
    maxStations integer
)
    returns table
            (
                id       bigint,
                name     varchar,
                address  varchar,
                distance double precision
            )
    language plpgsql
as
$$
begin
    RETURN Query (SELECT station.id, station.station_name, station.address, ST_Distance(location, poi) AS distance
                  FROM station,
                       (select ST_MakePoint(lon, lat) ::geography as poi) as poi
                  ORDER BY ST_Distance(location, poi)
                  LIMIT maxStations);
end;
$$;
