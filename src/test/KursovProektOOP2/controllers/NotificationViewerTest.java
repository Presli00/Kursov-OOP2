package KursovProektOOP2.controllers;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationViewerTest {

    private static final Logger log = Logger.getLogger(Main.class);
    NotificationViewer notificationViewer;
    @BeforeEach
    void setUp() {
        notificationViewer=new NotificationViewer();
    }

    @Test
    void markAsRead() {
        try{
            notificationViewer.MarkAsRead();
        }catch(Exception ex){
            log.error("Notifications marking unsuccessful " + "\n" + ex.getMessage());
        }finally {
            assertEquals("Notifications marking unsuccessful",log);
        }
    }

    @Test
    void deleteNotification() {
        try{
            notificationViewer.DeleteNotification();
        }catch (Exception ex){
            log.error("Notifications deletion unsuccessful " + "\n" + ex.getMessage());
        }finally {
            assertEquals("Notifications deletion unsuccessful",log);
        }
    }
}