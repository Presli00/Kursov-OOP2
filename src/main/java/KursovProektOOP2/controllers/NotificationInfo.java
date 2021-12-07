package KursovProektOOP2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class NotificationInfo {
    int id;
    @FXML
    Text notificationText;
    @FXML
    Label timestampLabel;
    @FXML
    ImageView isReadDot;
    @FXML
    CheckBox notificationCheck;


}
