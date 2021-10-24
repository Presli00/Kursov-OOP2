import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import java.util.stream.Stream;


public class OwnerGUI {

    // Panes
    @FXML
    AnchorPane ContentAnchorPane;

    // Buttons
    @FXML
    Button SpravkiButton;

    // Functions

    @FXML
    public void SpravkiOnAction(ActionEvent event) {
        // create Scroll Pane
        ScrollPane SpravkiScrollPane = new ScrollPane();
        // add it to Anchor Pane and stretch it to fit parent
        ContentAnchorPane.getChildren().add(SpravkiScrollPane);
        SpravkiScrollPane.prefWidthProperty().bind(ContentAnchorPane.widthProperty());
        SpravkiScrollPane.prefHeightProperty().bind(ContentAnchorPane.heightProperty());
        SpravkiScrollPane.setFitToWidth(true);
        SpravkiScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // disable horizontal bar
        // create buttons
        Button bt1 = new Button();
        bt1.setPrefHeight(200);
        Button bt2 = new Button();
        bt2.setPrefHeight(200);
        Button bt3 = new Button();
        bt3.setPrefHeight(200);
        // add Anchor Pane for content and stretch to fit Scroll Pane
        AnchorPane ScrollAnchorPane = new AnchorPane();
        SpravkiScrollPane.setContent(ScrollAnchorPane);
        ScrollAnchorPane.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        ScrollAnchorPane.prefHeightProperty().bind(SpravkiScrollPane.heightProperty());
        // Stretch buttons to fit width of scroll pane and add background image
        bt1.prefWidthProperty().bind(ScrollAnchorPane.widthProperty());
        bt2.prefWidthProperty().bind(ScrollAnchorPane.widthProperty());
        bt3.prefWidthProperty().bind(ScrollAnchorPane.widthProperty());
        Stream.of(bt1, bt2, bt3).forEach(button ->
                button.getStyleClass().add("SpravkiButtons"));
        bt1.setPadding(new Insets(0));
        bt1.setText("Собствени налични складове за отдаване под наем");
        bt2.setText("Складови агенти");
        bt3.setText("Складове на чужди собственици");
        // VBox to position buttons
        VBox ScrollVbox = new VBox();
        ScrollAnchorPane.getChildren().add(ScrollVbox);
        ScrollVbox.getChildren().addAll(bt1, bt2, bt3);
        ScrollVbox.setSpacing(30);
    }

}
