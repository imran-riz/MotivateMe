import 'package:flutter/material.dart';
import 'package:motivate_me/pages/home.dart';
import 'package:motivate_me/pages/search.dart';
import 'package:motivate_me/util/data_access.dart';

void main() async {
  await DataAccess.getAllQuotes() ;
  
  runApp(MaterialApp(
    initialRoute: "/home",

    routes: {
      "/home": (context) => Home(),
      "/search": (context) => Search(),
    }
  )) ;
}


