package KursovProektOOP2.controllers;

import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WarehouseViewer {
    @FXML
    ScrollPane ScrollPane;
    @FXML
    AnchorPane AnchorPane;
    @FXML
    VBox Vbox;
    Stage stage = new Stage();

    @FXML
    private void initialize(){
        if(UserSession.getRoleID().getRoleName().equals("Admin")){
            ToolBar toolBar = new ToolBar();
            toolBar.setPrefHeight(45);
            toolBar.setPrefWidth(200);
            toolBar.setStyle("-fx-background-color: #4E97D1; -fx-background-radius: 20 ");
            Button addWarehouse = new Button("Добави Склад ");
            addWarehouse.setOnAction(e->{
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/Views/SharedViews/WarehouseAdderForm.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Warehouse Form");
                stage.setResizable(false);
                stage.show();
            });
            addWarehouse.setStyle("-fx-background-radius: 70; -fx-font-family: Tahoma; -fx-font-size: 16px;");
            toolBar.getItems().add(addWarehouse);
            Vbox.getChildren().add(toolBar);
        }
        
        ScrollPane.widthProperty().addListener(event -> {
            AnchorPane.setPrefWidth(ScrollPane.getWidth());
        });
        ScrollPane.heightProperty().addListener(event -> {
            AnchorPane.setPrefHeight(ScrollPane.getHeight());
        });
    }
}
