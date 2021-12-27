package KursovProektOOP2.controllers;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuTest {
    private LoginMenu loginMenu;
    private static final Logger log = Logger.getLogger(Main.class);
    @BeforeEach
    void setUp(){
        loginMenu=new LoginMenu();
    }

    @Test
    void login() {
        try{
            loginMenu.login();
        }catch(Exception ex){
            log.error("Login unsuccessful" + ex.getMessage());
        }finally {
            assertEquals("Login unsuccessful",log);
        }
    }
}