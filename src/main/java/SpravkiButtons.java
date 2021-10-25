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
