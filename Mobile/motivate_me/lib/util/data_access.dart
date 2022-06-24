import 'dart:convert';
import 'dart:math';

import 'package:motivate_me/util/quote.dart';
import 'package:http/http.dart' ;

class DataAccess {
  static List<Quote> quotesList = [] ;

  static Future<List<Quote>> getAllQuotes() async {
    
    if (quotesList.isEmpty) {
      quotesList = await _getAllQuotesFromServer() ;
    }

    return quotesList ;
  }

  static Future<List<Quote>> _getAllQuotesFromServer() async {
  
    try {
      Response response = await get(Uri.parse("http://10.0.2.2:8080/motivate/me/quotes/all")).timeout(const Duration(seconds: 15)) ;

      List dataList = jsonDecode(response.body) ;

      for (int i = 0 ; i < dataList.length ; i++) {
        Map data = dataList[i] ;
        Quote quote = Quote(id: data['id'], quotation: data['quotation'], author: data['author'], category: data['category']) ;
        quotesList.add(quote) ;
      }

      print("Quotes obtained successfully!") ;
    }
    catch (e) {
      print("\nExceptions in DataAccess.getAllQuotes() -> ") ;
      print(e.toString()) ;

      quotesList.clear() ;
      quotesList.add(Quote(id: "ERROR", quotation: e.toString(), author: "ERROR", category: "ERROR")) ;
    }

    return quotesList ;
  }


  static Quote getRandomQuote() {
    int n = Random().nextInt(quotesList.length) ;
    return quotesList[n] ;
  }


  static List<Quote> getQuotesBy(String author) {
    List<Quote> results = quotesList.where((element) => element.author.toLowerCase().contains(author.toLowerCase())).toList() ;

    if (results.isEmpty) {
      results.add(Quote(id: "-", quotation: "<< no qoute by $author found! >>", author: "-", category: "-")) ;
    }

    return results ;
  }


  static List<Quote> getQuotesOn(String category) {
    List<Quote> results = quotesList.where((element) => element.category.toLowerCase().contains(category.toLowerCase())).toList() ;

    if(results.isEmpty) {
      results.add(Quote(id: "-", quotation: "<< no quote on $category found! >>", author: "-", category: "-")) ;
    }

    return results ;
  }


  static Quote getQuote(String id) {

    var results = quotesList.where((element) => element.id == id) ;

    if (results.isEmpty) {
      return Quote(id: "", quotation: "<< no quote found with id $id", author: "", category: "") ;
    }

    return results.first ;
  }
}