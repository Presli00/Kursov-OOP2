import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class SpravkiButtons {
    FXMLLoader loader;
    AdminGUI AdminGuiController;
    OwnerGUI OwnerGuiController;
    WarehouseAgentGUI WarehouseAgentGuiController;
    ArrayList<Button> buttons = new ArrayList<Button>();

    public void loadController(String GUItoLoad, int numOfButtons) throws IOException, NoSuchFieldException, IllegalAccessException {
        switch (GUItoLoad){
            case "OwnerGUI":
                loader = new FXMLLoader(getClass().getResource("OwnerGUI.fxml"));
                loader.load();
                OwnerGuiController = loader.getController();
                createPanes(OwnerGuiController);
                CreateButtons(numOfButtons);
                break;
            case "AdminGUI":
                loader = new FXMLLoader(getClass().getResource("AdminGUI.fxml"));
                loader.load();
                AdminGuiController = loader.getController();
                CreateButtons(numOfButtons);
                break;
            case "WarehouseAgentGUI":
                loader = new FXMLLoader(getClass().getResource("WarehouseAgentGUI.fxml"));
                loader.load();
                WarehouseAgentGuiController = loader.getController();
                CreateButtons(numOfButtons);
                break;
        }

    }

    public void createPanes(Object controller) throws NoSuchFieldException, IllegalAccessException {
        // create Scroll Pane
        ScrollPane SpravkiScrollPane = new ScrollPane();

        // add it to MAIN Anchor Pane and stretch it to fit parent
        Field ContentAnchorPane = controller.getClass().getDeclaredField("ContentAnchorPane");
        Field ScrollAnchorPane = controller.getClass().getDeclaredField("ScrollAnchorPane");

        ((AnchorPane)ContentAnchorPane.get(OwnerGuiController)).getChildren().add(SpravkiScrollPane);
        ((AnchorPane)ScrollAnchorPane.get(OwnerGuiController)).prefWidthProperty().bind(((AnchorPane)ContentAnchorPane.get(OwnerGuiController)).widthProperty());
        SpravkiScrollPane.prefHeightProperty().bind(((AnchorPane)ContentAnchorPane.get(OwnerGuiController)).heightProperty());
        SpravkiScrollPane.setFitToWidth(true);
        SpravkiScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // disable horizontal bar

        // add new Anchor Pane for content and stretch to fit Scroll Pane
        SpravkiScrollPane.setContent(((AnchorPane)ScrollAnchorPane.get(OwnerGuiController)));
        ((AnchorPane)ScrollAnchorPane.get(OwnerGuiController)).prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        ((AnchorPane)ScrollAnchorPane.get(OwnerGuiController)).prefHeightProperty().bind(SpravkiScrollPane.heightProperty());
    }

    public void CreateButtons(int numOfButtons) {
        for (int i = 0; i < numOfButtons; i++) {
            Button btn = new Button();
            btn.setPrefHeight(200);
            btn.getStyleClass().add("SpravkiButtons");
            buttons.add(btn);
        }

    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }
}
