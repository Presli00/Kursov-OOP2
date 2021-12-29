package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.util.Panes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class OwnerSpravki {
    @FXML
    AnchorPane anchorPane;

    @FXML
    private void agentSpravka() throws IOException {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/RatingSpravka.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, anchorPane);
    }

    @FXML
    private void warehouseSpravka() throws IOException{
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/Views/Spravki/OtherOwnersWarehouses.fxml")); //LOAD VIEW
        Panes.setAndClearScrollPane(sp, anchorPane);
    }
}
