package KursovProektOOP2.controllers;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main main;
    Stage primaryStage;
    @BeforeEach
    void setUp() {
        main=new Main();
    }

    @Test
    void start() throws IOException {
        main.start(primaryStage);

    }
}