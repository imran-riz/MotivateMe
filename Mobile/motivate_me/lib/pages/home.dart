import 'package:flutter/material.dart' ;
import 'package:motivate_me/util/data_access.dart';
import 'package:motivate_me/util/quote.dart';

class Home extends StatefulWidget {
  @override
  State<Home> createState() => _HomeState();
}


class _HomeState extends State<Home> {
  late Quote randomQuote ;
  String quotationToDisplay = "" ;
  String authorToDisplay = "" ;

  @override
  Scaffold build(BuildContext context) {
    randomQuote = DataAccess.getRandomQuote() ;
    quotationToDisplay = randomQuote.quotation ;
    authorToDisplay = "~ ${randomQuote.author}"  ;

    return Scaffold(
      backgroundColor: Colors.grey[900],

      appBar: AppBar(
        title: const Text("Motivate Me"),
        centerTitle: true,
        automaticallyImplyLeading: false,
        leading: IconButton(
            onPressed: (){
              // push the search page up
              Navigator.pushNamed(context, "/search") ;
            }, 
            icon: const Icon(Icons.search),
        ),
        backgroundColor: Colors.grey[800],
        elevation: 0.0,
      ),

      body: Stack(
        children: <Widget> [
          Container(
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage('assets/images/motivate-me-background.jpg'),
                fit: BoxFit.cover
              ),
            ),
          ),

          Padding(
            padding: const EdgeInsets.all(10),
    
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,

              children: <Widget> [
                Flexible(
                  flex: 3,
                  child: Text(
                    quotationToDisplay,
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                      fontFamily: "Bangers",
                      fontSize: 35.0,
                    ),
                  ),
                ),

                const SizedBox(height: 10.0),

                Flexible(
                  flex: 1,
                  child: Text(
                    authorToDisplay,
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                      fontFamily: "Roboto-Light",
                      fontStyle: FontStyle.italic,
                      fontSize: 20.0,
                    ),
                  ),
                )
              ],
            ),
          ),
        ],
      ),

      floatingActionButton: FloatingActionButton(
        onPressed: () {
          setState(() {
            randomQuote = DataAccess.getRandomQuote() ;  
          });
        },
        tooltip: 'refresh quote',
        backgroundColor: Colors.black,
        child: const Icon(Icons.refresh_sharp),
      ),
    ) ;    
  }
}