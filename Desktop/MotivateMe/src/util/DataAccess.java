package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Quote;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class DataAccess {

    private static HttpClient httpClient ;


    public static Quote getRandomQuote() {

        Quote quote ;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build() ;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/motivate/me/quote"))
                    .build() ;

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()) ;

            // convert the body of the HttpResponse to JSON
            JSONObject jsonObject = new JSONObject(response.body()) ;


            // extract the attributes of the Quote from the JSON
            quote = new Quote(jsonObject.getString("id"), jsonObject.getString("quotation"), jsonObject.getString("author"),  jsonObject.getString("category")) ;
        }
        catch (Exception e) {
            System.out.println("\nExceptions in DataAccess.getRandomQuote():\n" + e.getMessage() + "\n") ;
            quote = new Quote("ERROR", e.getMessage(), "ERROR", "ERROR") ;
        }

        return quote ;
    }


    public static ObservableList<Quote> getAllQuotes() {

        ObservableList<Quote> quotesList = FXCollections.observableArrayList() ;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build() ;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/motivate/me/quotes/all"))
                    .build() ;

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()) ;


            JSONArray array = new JSONArray(response.body()) ;

            for(int i = 0 ; i < array.length() ; i++) {
                JSONObject jsonObject = array.getJSONObject(i) ;

                quotesList.add(new Quote(jsonObject.getString("id"), jsonObject.getString("quotation"), jsonObject.getString("author"),  jsonObject.getString("category"))) ;
            }
        }
        catch (Exception e) {
            System.out.println("\nExceptions in  DataAccess.getAllQuotes():\n" + e.getMessage() + "\n") ;

            quotesList.clear() ;
            quotesList.add(new Quote("ERROR", e.getMessage(), "ERROR", "ERROR")) ;
        }

        return quotesList ;
    }


    /**
     * Used to obtain all the quotes by a specific author
     * @param nameOfAuthor the name of the desired author
     * @return a list of Quotes
     */
    public static ObservableList<Quote> getQuotesFrom(String nameOfAuthor) {

        ObservableList<Quote> quotesList = FXCollections.observableArrayList() ;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build() ;

        nameOfAuthor = nameOfAuthor.replaceAll(" ", "%20") ;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/motivate/me/quote/author/all/?author=" + nameOfAuthor))
                    .build() ;

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()) ;


            JSONArray array = new JSONArray(response.body()) ;

            for(int i = 0 ; i < array.length() ; i++) {
                JSONObject jsonObject = array.getJSONObject(i) ;

                quotesList.add(new Quote(jsonObject.getString("id"), jsonObject.getString("quotation"), jsonObject.getString("author"),  jsonObject.getString("category"))) ;
            }
        }
        catch (Exception e) {
            System.out.println("\nExceptions in  DataAccess.getQuotesFrom(String nameOfAuthor):\n" + e.getMessage() + "\n") ;

            quotesList.clear() ;
            quotesList.add(new Quote("ERROR", e.getMessage(), "ERROR", "ERROR")) ;
        }

        return quotesList ;
    }


    public static ObservableList<Quote> getQuotesOn(String category) {

        ObservableList<Quote> quotesList = FXCollections.observableArrayList() ;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build() ;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/motivate/me/quote/category/all/?category=" + category))
                    .build() ;

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()) ;


            JSONArray array = new JSONArray(response.body()) ;

            for(int i = 0 ; i < array.length() ; i++) {
                JSONObject jsonObject = array.getJSONObject(i) ;

                quotesList.add(new Quote(jsonObject.getString("id"), jsonObject.getString("quotation"), jsonObject.getString("author"),  jsonObject.getString("category"))) ;
            }
        }
        catch (Exception e) {
            System.out.println("\nExceptions in  DataAccess.getQuotesOn(String category):\n" + e.getMessage() + "\n") ;

            quotesList.clear() ;
            quotesList.add(new Quote("ERROR", e.getMessage(), "ERROR", "ERROR")) ;
        }

        return quotesList ;
    }


    public static Quote getQuoteWith(String id) {
        Quote quote ;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build() ;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8080/motivate/me/quote/id/?id=" + id))
                    .build() ;

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()) ;

            JSONObject jsonObject = new JSONObject(response.body()) ;

            quote = new Quote(jsonObject.getString("id"), jsonObject.getString("quotation"), jsonObject.getString("author"),  jsonObject.getString("category")) ;
        }
        catch (Exception e) {
            quote = new Quote("ERROR", e.getMessage(), "ERROR", "ERROR") ;
            System.out.println("\nExceptions in  DataAccess.getQuoteWith(String id):\n" + e.getMessage() + "\n") ;
        }

        return quote ;
    }
}
