import 'package:flutter/material.dart';
import 'package:motivate_me/pages/home.dart';
import 'package:motivate_me/pages/search.dart';
import 'package:motivate_me/pages/loading.dart';

void main() async {  
  runApp(MaterialApp(
    initialRoute: "/loading",

    routes: {
      "/loading": (context) => Loading(),
      "/home": (context) => Home(),
      "/search": (context) => Search(),
    }
  )) ;
}


