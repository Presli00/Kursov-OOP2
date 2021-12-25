package KursovProektOOP2.controllers;
import KursovProektOOP2.util.Constants;
import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuTest {
    private LoginMenu loginMenu;
    Stage primaryStage;
    private static final Logger log = Logger.getLogger(Main.class);
    @BeforeEach
    void setUp() throws IOException {
        loginMenu=new LoginMenu();
        PropertyConfigurator.configure(Main.class.getResource(Constants.Configuration.LOG4J_PROPERTIES));
        URL path = getClass().getResource("./Views/LoginViews/LoginMenu.fxml");
        if (path != null) {
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./Images/PR Warehouses.png")));
            Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
            StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("./Styles/generalStyling.css").toString());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } else {
            log.error("View couldn't be loaded");
            loginMenu.usernameTF.setText("Grumboll");
            loginMenu.passwordTF.setText("1234");
        }
    }

    @Test
    void login() {
        loginMenu.login();
        if(loginMenu.errorLabel.toString()=="") {
            assertEquals("You entered wrong information!", loginMenu.errorLabel.toString());
        }
    }
}