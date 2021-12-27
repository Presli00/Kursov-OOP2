package KursovProektOOP2.controllers.Admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationFormTest {

    private RegistrationForm registrationForm;
    @BeforeEach
    void setUp() {
        registrationForm=new RegistrationForm();
    }

    @Test
    void validate() {
        registrationForm.Register();
        assertEquals("Didn't choose the need information",registrationForm.validationLabel.toString());
    }
}