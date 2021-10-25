import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class SpravkiButtons {
    FXMLLoader loader;
    AdminGUI AdminGuiController;
    OwnerGUI OwnerGuiController;
    WarehouseAgentGUI WarehouseAgentGuiController;
    ArrayList<Button> buttons = new ArrayList<Button>();

    public void loadController(String GUItoLoad, int numOfButtons) throws IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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

    public void createPanes(Object controller) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // create Scroll Pane
        ScrollPane SpravkiScrollPane = new ScrollPane();
        // add it to MAIN Anchor Pane and stretch it to fit parent
        AnchorPane ContentAnchorPane = (AnchorPane) controller.getClass().getMethod("getContentAnchorPane").invoke(controller);
        AnchorPane ScrollAnchorPane = (AnchorPane) controller.getClass().getMethod("getScrollAnchorPane").invoke(controller);
        System.out.println("ass");
        ContentAnchorPane.getChildren().add(SpravkiScrollPane);
        ScrollAnchorPane.prefWidthProperty().bind(ContentAnchorPane.widthProperty());
        SpravkiScrollPane.prefHeightProperty().bind(ContentAnchorPane.heightProperty());
        SpravkiScrollPane.setFitToWidth(true);
        SpravkiScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // disable horizontal bar

        // add new Anchor Pane for content and stretch to fit Scroll Pane
        SpravkiScrollPane.setContent(ScrollAnchorPane);
        ScrollAnchorPane.prefWidthProperty().bind(SpravkiScrollPane.widthProperty());
        ScrollAnchorPane.prefHeightProperty().bind(SpravkiScrollPane.heightProperty());
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
