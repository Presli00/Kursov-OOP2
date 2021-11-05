import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AdminGUI {
    // Panes
    @FXML
    AnchorPane ContentAnchorPane;
    AnchorPane ScrollAnchorPane = new AnchorPane();
    // Buttons
    @FXML
    Button SpravkiButton;

    @FXML
    public void SpravkiOnAction() throws IOException{
        ScrollPane SpravkiScrollPane = new ScrollPane();
        ContentAnchorPane.getChildren().add(SpravkiScrollPane);
        SpravkiScrollPane.prefHeightProperty().bind(ContentAnchorPane.heightProperty());
        SpravkiScrollPane.prefWidthProperty().bind(ContentAnchorPane.widthProperty());
        SpravkiScrollPane.setFitToWidth(true);
        SpravkiScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        SpravkiScrollPane.setContent(ScrollAnchorPane);
        ScrollAnchorPane.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        ScrollAnchorPane.prefHeightProperty().bind(SpravkiScrollPane.heightProperty());
        SpravkiButtons obj = new SpravkiButtons();
        obj.loadController("AdminGUI", 5);
        ArrayList<Button> buttons = obj.getButtons();
        StyleButtons(buttons);
        VBox ScrollVbox = new VBox();
        ScrollAnchorPane.getChildren().add(ScrollVbox);
        ScrollVbox.getChildren().addAll(buttons);
        ScrollVbox.setSpacing(30);
    }

    public void StyleButtons(ArrayList<Button> buttons) {
        String[] Text = {"Сключени договори", "Уредници", "Рейтинги на агенти", "Складове на чужди собственици", "Складове по даден собственик"};
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).prefWidthProperty().bind(ScrollAnchorPane.widthProperty());
            buttons.get(i).setPadding(new Insets(0));
            buttons.get(i).setText(Text[i]);
        }
    }
}
