import 'package:flutter/material.dart';
import 'package:motivate_me/util/data_access.dart';
import 'package:motivate_me/util/quote.dart';
import 'package:motivate_me/util/quote_box.dart';


class Search extends StatefulWidget {
  @override
  State<Search> createState() => _SearchState();
}

class _SearchState extends State<Search> {

  final List<Quote> _quote_list = DataAccess.quotesList ;

  @override
  Scaffold build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Search Quotes"),
        backgroundColor: Colors.grey[800],
        elevation: 0.0,
        centerTitle: true,

        actions: [
          IconButton(
            onPressed: (){
              showSearch(context: context, delegate: CustomSearchDelegate()) ;
            },
            icon: const Icon(Icons.search)
          )
        ],
      ),

      body: Padding (
        padding: const EdgeInsets.all(5.0),
        child: ListView.builder (
          itemCount: _quote_list.length,
          itemBuilder: (BuildContext context, int index) { 
            return QuoteBox(quote: _quote_list[index]) ;
          }
        ),
      ),

      backgroundColor: Colors.white,
    ) ;
  }
}


class CustomSearchDelegate extends SearchDelegate {

  List<Quote> searchItems = DataAccess.quotesList ;     // an attribute that contains all the quotes from the database

  @override
  List<Widget>? buildActions(BuildContext context) {
    return [
      IconButton(
        icon: const Icon(Icons.clear), 
        onPressed: (){
          query = "" ;
        },         
      )
    ] ;
  }

  @override
  Widget? buildLeading(BuildContext context) {
    return IconButton(
      icon: const Icon(Icons.arrow_back),
      onPressed: () {
        close(context, null) ;
      }
    ) ;
  }

  @override
  Widget buildResults(BuildContext context) {
    List<Quote> results = [] ;

    for(var quote in searchItems) {
      if(quote.quotation.toLowerCase().contains(query.toLowerCase()) ||  
         quote.author.toLowerCase().contains(query.toLowerCase()) || 
         quote.category.toLowerCase().contains(query.toLowerCase())) {
        results.add(quote) ;
      }
    }

    return ListView.builder(
      itemCount: results.length,
      itemBuilder: (context, index) {
        return QuoteBox(quote: results[index]) ;
      },
    ) ;
  }

  @override
  Widget buildSuggestions(BuildContext context) {
    List<Quote> results = [] ;

    for(var quote in searchItems) {
      if(quote.quotation.toLowerCase().contains(query.toLowerCase()) ||  
         quote.author.toLowerCase().contains(query.toLowerCase()) || 
         quote.category.toLowerCase().contains(query.toLowerCase())) {
        results.add(quote) ;
      }
    }

    return ListView.builder(
      itemCount: results.length,
      itemBuilder: (context, index) {
        return QuoteBox(quote: results[index]) ;
      },
    ) ;
  }

}