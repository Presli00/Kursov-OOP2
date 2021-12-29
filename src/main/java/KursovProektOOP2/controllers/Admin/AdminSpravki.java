package KursovProektOOP2.controllers.Admin;

import KursovProektOOP2.util.Panes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminSpravki {

    @FXML
    AnchorPane AnchorPane;

    private void clearPane(){
        if (!AnchorPane.getChildren().isEmpty()) {
            AnchorPane.getChildren().clear();
        }
    }

    @FXML
    private void formularsSpravka() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/formularsSpravki.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, AnchorPane);
    }

    @FXML
    private void maintenanceSpravka()throws IOException{
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/MaintenanceSpravka.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, AnchorPane);
    }

    @FXML
    private void agentSpravka() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/RatingSpravka.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, AnchorPane);
    }

    @FXML
    private void warehouseSpravka(){

    }
}
