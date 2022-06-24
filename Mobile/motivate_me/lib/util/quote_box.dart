import 'package:flutter/material.dart';
import 'package:motivate_me/util/quote.dart';


class QuoteBox extends StatelessWidget {

  late Quote _quote ;
  Color? _color = Colors.black ;
    
  QuoteBox({required quote}) {
    _quote = quote ;

    if (int.parse(_quote.id) % 2 == 0) {
      _color = Colors.grey[600] ;
    }
    else {
      _color = Colors.grey[700] ;
    }
  } 

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(5.0),
      child: Container (
        height: 200,

        decoration: BoxDecoration(
          color: _color,
          borderRadius: BorderRadius.only(
            topRight: int.parse(_quote.id) % 2 == 0 ? const Radius.circular(0.0) : const Radius.circular(45.0),
            bottomLeft: int.parse(_quote.id) % 2 == 0 ? const Radius.circular(0.0) : const Radius.circular(45.0),
            topLeft: int.parse(_quote.id) % 2 == 0 ? const Radius.circular(45.0) : const Radius.circular(0.0),
            bottomRight: int.parse(_quote.id) % 2 == 0 ? const Radius.circular(45.0) : const Radius.circular(0)
          ),
        ),

        padding: const EdgeInsets.only(
          left: 15.0,
          top: 20.0,
          right: 15.0,
          bottom:20.0
        ),
        
        child: Stack (
          children: [
            Positioned(
              top: 5.0, left: 2.0, right: 2.0,
              child: RichText(
                text: TextSpan(
                  text: "\"${_quote.quotation}\"",
                  style: const TextStyle(
                    fontFamily: "Bangers-Regular",
                    fontSize: 18.0,
                    fontWeight: FontWeight.bold
                  )
                )
              )
            ),

            Positioned(
              bottom: 5.0, left: 2.0, right: 100.0,
              child: RichText(
                text: TextSpan(
                  text: "by ${_quote.author}",
                  style: const TextStyle(
                    fontFamily: "Robot-light",
                    fontSize: 15.0
                  )
                )
              )
            ),

            Positioned(
              bottom: 5.0, right: 2.0,
              child: RichText(
                text: TextSpan(
                  text: "Category: ${_quote.category}",
                  style: const TextStyle(
                    fontFamily: "Robot-light",
                    fontSize: 15.0
                  )
                )
              )
            ),
          ],
        )
      ),
    ) ;
  }
} 