package pages;


import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import model.Quote;
import util.DataAccess;
import util.PageController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class HomePageController implements Initializable {

    public AnchorPane rootPane ;
    public Button searchPageBtn, refreshBtn ;
    public VBox vbox ;
    public TextFlow textFlow ;

    private Text quotationText, authorText ;
    private Quote randomQuote ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // get the random quote
        this.randomQuote = DataAccess.getRandomQuote() ;


        // set up the Texts and then the TextFlow object
        quotationText = new Text(this.randomQuote.getQuotation() + "\n") ;
        quotationText.setWrappingWidth(700.0) ;

        authorText = new Text("~ " + this.randomQuote.getAuthor()) ;

        textFlow.setTextAlignment(TextAlignment.CENTER) ;
        textFlow.getChildren().addAll(quotationText, authorText) ;



        // set up the refresh button with its graphic
        ImageView imageView1 = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResource("/resources/images/refresh-white.png")).toString()));
        imageView1.setFitHeight(20.0) ;
        imageView1.setFitWidth(20.0) ;
        refreshBtn.setGraphic(imageView1) ;

        // set up the search button with its graphic
        ImageView imageView2 = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResource("/resources/images/search-white.png")).toString()));
        imageView2.setFitHeight(20.0) ;
        imageView2.setFitWidth(20.0) ;
        searchPageBtn.setGraphic(imageView2) ;
        searchPageBtn.setShape(new Circle(2.0)) ;

        // set the style classes for the Text objects
        quotationText.getStyleClass().add("quotation-text-style") ;
        authorText.getStyleClass().add("author-text-style") ;
    }


    /**
     * Obtains a new random quote and update the text area in the home page
     */
    public void refresh() {
        Quote existingQuote = randomQuote ;
        randomQuote = DataAccess.getRandomQuote() ;

        while (existingQuote.getQuotation().equalsIgnoreCase(randomQuote.getQuotation())) {
            randomQuote = DataAccess.getRandomQuote() ;
        }

        quotationText.setText(this.randomQuote.getQuotation()) ;
        authorText .setText("\n~ " + this.randomQuote.getAuthor()) ;
    }


    /**
     *
     */
    public void loadSearchPage() {
        PageController.activate("search_page") ;
    }
}
