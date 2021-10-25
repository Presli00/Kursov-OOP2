import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import java.io.IOException;
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
    public void SpravkiOnAction(ActionEvent event) throws IOException, NoSuchFieldException, IllegalAccessException {
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

    public void StyleButtons(ArrayList<Button> btns){
        // Stretch buttons to fit width of scroll pane and add background image
        String[] Text = {"Собствени налични складове за отдаване под наем", "Складови агенти", "Складове на чужди собственици"};
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).prefWidthProperty().bind(ScrollAnchorPane.widthProperty());
            btns.get(i).setPadding(new Insets(0));
            btns.get(i).setText(Text[i]);
        }
    }
}
