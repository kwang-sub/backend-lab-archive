SELECT ST_PointFromText('POINT(10.123 20.1241)', 4326);
SELECT ST_X(ST_PointFromText('POINT(10.123 20.1241)', 4326));

set @line := ST_LineStringFromText('LINESTRING(37.55601011174991 127.03600689589169,
                                                37.55601011174991 127.03600689589169,
                                                37.55601011174991 127.03600689589169,
                                                37.55601011174991 127.03600689589169')