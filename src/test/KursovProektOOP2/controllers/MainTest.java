package KursovProektOOP2.controllers;

import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final Logger log = Logger.getLogger(Main.class);
    private Main main;
    Stage primaryStage;
    @BeforeEach
    void setUp() {
        main=new Main();
    }

    @Test
    void start() throws IOException {
        try {
            main.start(primaryStage);
        }catch (Exception ex) {
            log.error("View couldn't be loaded");
        }finally {
            assertEquals("View couldn't be loaded",log);
        }
    }
}