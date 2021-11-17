package KursovProektOOP2.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = getClass().getResource("/Views/AdminGUI.fxml");

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("/Styles/SpravkiStyles.css").toExternalForm()); // ADD CSS TO SCENE
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/PR Warehouses.png")));
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(650);
            primaryStage.setMinWidth(1200);
            primaryStage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }

    }
}
