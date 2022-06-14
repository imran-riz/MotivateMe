import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Page;
import util.PageController;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;


public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args) ;
    }

    /**
     * Method to check if the device is connected to the Internet
     * @return true if connected else false
     */
    private static Boolean isConnected() {
        boolean connected ;

        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();

            connected = true ;
        }
        catch (IOException e) {
            connected = false ;
        }

        return connected ;
    }


    @Override
    public void start(Stage stage) throws IOException {

        if (isConnected()) {
            // load all the Panes to be used
            Pane homeAnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/HomePage.fxml"))) ;
            Pane searchAnchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/SearchPage.fxml"))) ;

            // instantiate the Page objects
            Page homePage = new Page(homeAnchorPane, "/resources/styles/home_page_stylesheet.css") ;
            Page searchPage = new Page(searchAnchorPane, "/resources/styles/search_page_stylesheet.css") ;

            // instantiate the Scene with an emtpy Pane
            Scene scene = new Scene(new Pane()) ;

            // set up the PageController
            PageController.setMainScene(scene) ;
            PageController.addPage("home_page", homePage) ;
            PageController.addPage("search_page", searchPage) ;

            // activate the home page using the PageController
            PageController.activate("home_page") ;

            stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/resources/images/app-icon.png")))) ;
            stage.setTitle("M O T I V A T E  M E") ;
            stage.setScene(scene) ;
            stage.setResizable(false) ;
            stage.show() ;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("ERROR") ;
            alert.setHeaderText("Device not connected to the Internet!") ;
            alert.setContentText("Retry once connected.") ;
            alert.showAndWait() ;
        }
    }
}
