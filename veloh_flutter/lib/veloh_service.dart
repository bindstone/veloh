import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:veloh_flutter/veloh_entity.dart';

class VelohService {
  final http.Client client;

  VelohService(this.client);

  Future<List<Station>> fetchStations() async {
    final response = await client.get(Uri.parse('http://localhost:8080/station/'));
    return parseStations(response.body);
  }

  List<Station> parseStations(dynamic responseBody) {
    if (responseBody.isNotEmpty) {
      var jsonArr = jsonDecode(responseBody);

      var stations = <Station>[];
      jsonArr.map((jsonObj) => Station.fromJson(jsonObj)).forEach((e) => stations.add((e as Station)));
      return stations;
    } else {
      return List.empty();
    }
  }
}
