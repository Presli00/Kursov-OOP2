import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class OwnerGUI {

    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    AnchorPane ScrollAnchorPane = new AnchorPane();
    // Buttons
    @FXML
    Button SpravkiButton;

    // Functions

    @FXML
    public void SpravkiOnAction() throws IOException{
        // create Scroll Pane
        ScrollPane SpravkiScrollPane = new ScrollPane();
        // add it to MAIN Anchor Pane and stretch it to fit parent
        ContentAnchorPane.getChildren().add(SpravkiScrollPane);
        SpravkiScrollPane.prefHeightProperty().bind(ContentAnchorPane.heightProperty());
        SpravkiScrollPane.prefWidthProperty().bind(ContentAnchorPane.widthProperty());
        SpravkiScrollPane.setFitToWidth(true);
        SpravkiScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // disable horizontal bar

        // add new Anchor Pane for content and stretch to fit Scroll Pane
        SpravkiScrollPane.setContent(ScrollAnchorPane);
        ScrollAnchorPane.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        ScrollAnchorPane.prefHeightProperty().bind(SpravkiScrollPane.heightProperty());
        SpravkiButtons obj = new SpravkiButtons();
        obj.loadController("OwnerGUI", 3);
        ArrayList<Button> btns = obj.getButtons();
        StyleButtons(btns);

        // VBox to position buttons
        VBox ScrollVbox = new VBox();
        ScrollAnchorPane.getChildren().add(ScrollVbox);
        ScrollVbox.getChildren().addAll(btns);
        ScrollVbox.setSpacing(30);
    }

    public void StyleButtons(ArrayList<Button> btns) {
        // Stretch buttons to fit width of scroll pane and add background image
        String[] Text = {"Собствени налични складове за отдаване под наем", "Складови агенти", "Складове на чужди собственици"};
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).prefWidthProperty().bind(ScrollAnchorPane.widthProperty());
            btns.get(i).setPadding(new Insets(0));
            btns.get(i).setText(Text[i]);
        }
    }

}
