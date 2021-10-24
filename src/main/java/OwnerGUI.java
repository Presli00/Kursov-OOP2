import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


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
        bt1.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        bt2.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        bt3.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        bt1.setStyle("-fx-background-image: url('/Images/warehousesWithLogo.png')");
        bt2.setStyle("-fx-background-image: url('/Images/warehousesWithLogo.png')");
        bt3.setStyle("-fx-background-image: url('/Images/warehousesWithLogo.png')");
        // VBox to position buttons
        VBox ScrollVbox = new VBox();
        ScrollAnchorPane.getChildren().add(ScrollVbox);
        ScrollVbox.getChildren().addAll(bt1, bt2, bt3);
        ScrollVbox.setSpacing(30);
        ScrollVbox.setPadding(new Insets(10, 20, 20, 20));
    }

}
