create or replace function fct_closest_stations(
    lat double precision,
    lng double precision,
    maxStations integer
)
returns table(name varchar, address varchar, distance double precision)
language plpgsql
as $$
begin
    RETURN Query (
    SELECT station.station_name, station.address, ST_Distance(location, poi) AS distance
    FROM station,
         (select ST_MakePoint(lat,lng)::geography as poi) as poi
    ORDER BY ST_Distance(location, poi)
    LIMIT maxStations
    );
end;$$;
