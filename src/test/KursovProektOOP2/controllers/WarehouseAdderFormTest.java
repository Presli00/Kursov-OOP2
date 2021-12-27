package KursovProektOOP2.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseAdderFormTest {

    private WarehouseAdderForm warehouseAdderForm;

    @BeforeEach
    void setUp() {
        warehouseAdderForm = new WarehouseAdderForm();
    }

    @Test
    void validate() {
        warehouseAdderForm.addWarehouse();
        assertEquals("Didn't choose the need information",warehouseAdderForm.errorLabel.toString());
    }
}