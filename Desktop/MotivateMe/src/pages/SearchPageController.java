package pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.Quote;
import util.DataAccess;
import util.PageController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class SearchPageController implements Initializable {

    public AnchorPane rootPane ;
    public Label searchLbl ;
    public ChoiceBox<String> choiceBox ;
    public TextField searchField ;
    public Button searchBtn, backBtn;
    public TableView<Quote> tableView ;
    public TableColumn<Quote, String> authorCol, quoteCol, categoryCol ;

    private ObservableList<Quote> listOfAllQuotes = FXCollections.observableArrayList() ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // fetch data from web API
        this.listOfAllQuotes = DataAccess.getAllQuotes() ;

        initializeTable() ;

        // initialise the Choice Box
        ObservableList<String> list = FXCollections.observableArrayList() ;
        list.addAll("author", "category", "id") ;
        choiceBox.setItems(list) ;
        choiceBox.setValue("author") ;

        // set up the TextField for searching
        searchField.setOnKeyPressed(keyEvent ->
        {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                search() ;
            }
            else if(keyEvent.getCode() == KeyCode.BACK_SPACE || keyEvent.getCode() == KeyCode.DELETE) {
                if (searchField.getText().isBlank()) {
                    tableView.setItems(this.listOfAllQuotes) ;
                }
            }
        }) ;

        // set up the back button with its graphic and shape
        ImageView imageView1 = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResource("/resources/images/back-arrow-white.png")).toString())) ;
        imageView1.setFitWidth(20.0) ;
        imageView1.setFitHeight(20.0) ;
        backBtn.setGraphic(imageView1) ;
        backBtn.setShape(new Circle(2.0)) ;

        ImageView imageView2 = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResource("/resources/images/search-white.png")).toString()));
        imageView2.setFitHeight(20.0) ;
        imageView2.setFitWidth(20.0) ;
        searchBtn.setGraphic(imageView2) ;


        searchLbl.setId("search_lbl_style") ;
    }


    /**
     * Used to initialise the TableView
     */
    private void initializeTable() {
        // initialise all the columns
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author")) ;
        quoteCol.setCellValueFactory(new PropertyValueFactory<>("quotation")) ;
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category")) ;

        categoryCol.setCellFactory(tc ->
        {
            Text text = new Text() ;

            TableCell<Quote, String> cell = new TableCell<>() ;
            cell.setGraphic(text) ;
            cell.setPrefHeight(Control.USE_PREF_SIZE) ;

            text.wrappingWidthProperty().bind(categoryCol.widthProperty()) ;
            text.textProperty().bind(cell.itemProperty()) ;

            return cell ;
        }) ;

        quoteCol.setCellFactory(tc ->
        {
            Text text = new Text() ;

            TableCell<Quote, String> cell = new TableCell<>() ;
            cell.setGraphic(text) ;
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE) ;

            text.wrappingWidthProperty().bind(quoteCol.widthProperty()) ;
            text.textProperty().bind(cell.itemProperty()) ;

            return cell ;
        }) ;

        authorCol.setCellFactory(tc ->
        {
            Text text = new Text() ;

            TableCell<Quote, String> cell = new TableCell<>() ;
            cell.setGraphic(text) ;
            cell.setPrefHeight(Control.USE_PREF_SIZE) ;

            text.wrappingWidthProperty().bind(authorCol.widthProperty()) ;
            text.textProperty().bind(cell.itemProperty()) ;

            return cell ;
        }) ;

        tableView.setItems(this.listOfAllQuotes) ;
    }



    /**
     * Used to obtain the quotes based on the search conditions
     */
    public void search() {
        String entry = searchField.getText() ;
        entry = entry.trim() ;

        if(!entry.isBlank()) {
            switch (choiceBox.getValue().toLowerCase()) {
                case "author" -> tableView.setItems(DataAccess.getQuotesFrom(entry));

                case "category" -> tableView.setItems(DataAccess.getQuotesOn(entry));

                case "id" -> {
                    ObservableList<Quote> newList = FXCollections.observableArrayList() ;
                    newList.add(DataAccess.getQuoteWith(entry)) ;
                    tableView.setItems(newList) ;
                }
            }
        }
    }


    public void goBack() {
        PageController.activate("home_page") ;
    }
}
