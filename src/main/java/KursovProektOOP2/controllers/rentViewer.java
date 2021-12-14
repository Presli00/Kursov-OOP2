package KursovProektOOP2.controllers;

import KursovProektOOP2.data.entity.Formular;
import KursovProektOOP2.data.repository.FormularRepository;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class rentViewer {
@FXML
    AnchorPane AnchorPane;
@FXML
    ScrollPane ScrollPane;
@FXML
    VBox Vbox;
    private static final Logger log = Logger.getLogger(Main.class);
    public final FormularRepository repository = FormularRepository.getInstance();
    List<Formular> formulars;
    @FXML
    public void initialize()throws IOException{

    }
}
