import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:veloh_flutter/veloh_entity.dart';

class VelohMap extends StatelessWidget {
  final List<Station> markers;

  const VelohMap(this.markers, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FlutterMap(
      options: MapOptions(
        center: LatLng(49.611622, 6.131935), // Luxembourg
        zoom: 16,
      ),
      layers: [
        TileLayerOptions(
          urlTemplate: "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
          subdomains: ['a', 'b', 'c'],
        ),
        MarkerLayerOptions(
          markers: markers
              .map(
                (m) => Marker(point: m.location.coordinates, width: 20, height: 20, builder: (c) => Container(
                  width: 20.0,
                  height: 20.0,
                  decoration: const BoxDecoration(
                    color: Colors.orange,
                    shape: BoxShape.circle,
                  ),
                ))
              ).toList(),
        )
      ],
      nonRotatedChildren: [
        AttributionWidget.defaultWidget(
          source: 'OpenStreetMap contributors',
          onSourceTapped: null,
        ),
      ],
    );
  }
}
