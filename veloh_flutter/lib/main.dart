import 'package:flutter/material.dart';
import 'package:veloh_flutter/veloh_entity.dart';
import 'package:veloh_flutter/veloh_map.dart';
import 'package:veloh_flutter/veloh_service.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final velohService = VelohService(http.Client());

  MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Veloh Map',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        useMaterial3: true
      ), home: FutureBuilder<List<Station>>(
      future: velohService.fetchStations(),
      builder: (context, snapshot) {
        if (snapshot.data != null) {
          return VelohMap(snapshot.data!);
        } else {
          return const SizedBox();
        }
      },
    ));
  }
}
