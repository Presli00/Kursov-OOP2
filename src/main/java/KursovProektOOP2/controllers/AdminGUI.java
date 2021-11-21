package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class AdminGUI {
    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    @FXML
    GridPane GridPane;
    // Buttons
    @FXML
    Button SpravkiButton;
    @FXML
    Button logOutButton;

    private static final Logger log = Logger.getLogger(Main.class);
    @FXML
    public void SpravkiOnAction() throws IOException {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("/Views/AdminViews/AdminSpravki.fxml")); //LOAD VIEW
        if (!ContentAnchorPane.getChildren().isEmpty()) {
            ContentAnchorPane.getChildren().clear();
        }
        ContentAnchorPane.getChildren().add(ap);
        ap.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
        ap.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            ap.setPrefWidth(ContentAnchorPane.getWidth());
        });

        ContentAnchorPane.heightProperty().addListener(event -> {
            ap.setPrefHeight(ContentAnchorPane.getHeight());
        });

    }

    public void AccountsOnAction() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/AdminViews/AdminAccountViewer.fxml")); //LOAD VIEW
        if (!ContentAnchorPane.getChildren().isEmpty()) {
            ContentAnchorPane.getChildren().clear();
        }
        ContentAnchorPane.getChildren().add(sp);
        sp.setPrefWidth(ContentAnchorPane.getWidth()); // SET SIZE OF VIEW
        sp.setPrefHeight(ContentAnchorPane.getHeight());
        ContentAnchorPane.widthProperty().addListener(event -> {
            sp.setPrefWidth(ContentAnchorPane.getWidth());
        });

        ContentAnchorPane.heightProperty().addListener(event -> {
            sp.setPrefHeight(ContentAnchorPane.getHeight());
        });

    }

    public void logOutOnAction() throws IOException {
        closeWindow();
        openWindow("/Views/LoginViews/LoginMenu.fxml");
    }

    public void closeWindow() {
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    public void openWindow(String pathToView) throws IOException {
        Stage stage = new Stage();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = getClass().getResource(pathToView);

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/PR Warehouses.png")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{
            log.error("View couldn't be loaded");
            System.exit(-1);

        }
    }
}
