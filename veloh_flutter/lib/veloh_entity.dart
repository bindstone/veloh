import 'package:latlong2/latlong.dart';

class Location {
  String type;
  LatLng coordinates;

  Location(this.type, this.coordinates);
  factory Location.fromJson(Map<dynamic, dynamic> json) {
    var coord = <double>[];
    json['coordinates'].forEach((e) => coord.add((e as double)));
    return Location(
        json['type'] as String,
        LatLng(coord[1], coord[0])
    );
  }
}

class Station {
  int id;
  String name;
  String address;
  Location location;

  Station(this.id, this.name, this.address, this.location);

  factory Station.fromJson(Map<dynamic, dynamic> json) {
    return Station(
        json['id'] as int,
        json['name'] as String,
        json['address'] as String,
        Location.fromJson(json['location'])
    );
  }
}
