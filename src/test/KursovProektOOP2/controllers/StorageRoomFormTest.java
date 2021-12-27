package KursovProektOOP2.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageRoomFormTest {

    private StorageRoomForm storageRoomForm;
    @BeforeEach
    void setUp() {
        storageRoomForm=new StorageRoomForm();
    }

    @Test
    void validate() {
        storageRoomForm.addRoom();
        assertEquals("Didn't choose the need information",storageRoomForm.errorLabel.toString());
    }
}