SELECT station.*, ST_Distance(location, poi)/1000 AS distance_km
FROM station,
     (select ST_MakePoint(-90,47)::geography as poi) as poi
-- WHERE ST_DWithin(location, poi, 100000)
ORDER BY ST_Distance(location, poi)
LIMIT 5;


SELECT station.*
FROM  station
ORDER BY location <-> st_setsrid(st_makepoint(-90,47),4326)
LIMIT 5;

SELECT station.*
FROM station
WHERE ST_DWithin(location, ST_GeographyFromText('SRID=4326;POINT(90 47)'), 1000000);
