package KursovProektOOP2.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class Panes {

    private static final Logger log = Logger.getLogger(Main.class);

    static void clearAnchorPane(AnchorPane ap, AnchorPane contentAnchorPane) {
        if(!contentAnchorPane.getChildren().isEmpty()) {
            contentAnchorPane.getChildren().clear();
        }
        contentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(contentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(contentAnchorPane.getHeight());
        contentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(contentAnchorPane.getWidth());
        });

        contentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(contentAnchorPane.getHeight());
        });
    }

    static void openWindow(String pathToView, Class c) throws IOException {
        Stage stage = new Stage();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = c.getResource(pathToView);

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(c.getResourceAsStream("/Images/PR Warehouses.png")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }

}
