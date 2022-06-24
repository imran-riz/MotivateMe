import 'package:flutter/material.dart' ; 
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:motivate_me/util/data_access.dart';


class Loading extends StatefulWidget {
  @override
  State<Loading> createState() => _LoadingState();
}


class _LoadingState extends State<Loading> {

  // this method is called only once - that's when the object is created for the very first time
  @override
  void initState() {    
    super.initState() ;
    loadAllQuotes() ;
  }

  // called when the object is created for the first time and every time the setState function is called
  @override  
  Widget build(BuildContext context) {
    return const Scaffold(
      backgroundColor: Colors.black,
      body: Center(
        child: SpinKitWave (
          color: Colors.white,
          size: 55.0,
        ),
      ),
    );
  }

  void loadAllQuotes() async {
    await DataAccess.getAllQuotes() ;

    if(DataAccess.quotesList.first.category == "ERROR") {
      _showAlert() ;
    }
    else {
      Navigator.pushReplacementNamed(context, '/home') ;
    }
  }


  void _showAlert() {
    showDialog(
      context: context, 
      builder: (context) {
        return AlertDialog(
          title: const Text("ERROR"),
          content: const Text("Failed to connect to the sever. Close the app, connect to the internet and try again later."),
          actions: [
            MaterialButton(
              child: const Text("Okay"),
              onPressed: () {
                Navigator.pop(context) ;
              }
            )
          ],
        ) ;
      }
    ) ;
  }
}
