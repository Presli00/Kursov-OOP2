package KursovProektOOP2.controllers.Agent;

import KursovProektOOP2.util.Panes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WarehouseAgentSpravki {
    @FXML
    AnchorPane AnchorPane;
    @FXML
    private void formularsSpravki() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/formularsSpravki.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, AnchorPane);
    }
    @FXML
    private void agentSpravka() throws IOException{
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/RatingSpravka.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, AnchorPane);
    }
}
