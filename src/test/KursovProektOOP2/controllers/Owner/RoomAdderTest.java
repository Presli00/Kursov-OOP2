package KursovProektOOP2.controllers.Owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomAdderTest {

    private RoomAdder roomAdder;
    @BeforeEach
    void setUp() {
        roomAdder=new RoomAdder();
    }

    @Test
    void validate() {
        roomAdder.Add();
        assertEquals("Didn't choose the need information",roomAdder.validationLabel.toString());
    }
}