package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(name = "notification", schema = "warehouse")
@Entity
public class Notification implements Serializable {
    @Id
    @Column(name = "notificationNameID", nullable = false)
    private int notificationNameID;
    @Column(name = "notificationName", nullable = false)
    private String notificationName;

    public int getNotificationNameID() {
        return notificationNameID;
    }

    public void setNotificationNameID(int notificationNameID) {
        this.notificationNameID = notificationNameID;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification notif = (Notification) o;

        if (notificationNameID != notif.notificationNameID) return false;
        if (notificationName != null ? !notificationName.equals(notif.notificationName) : notif.notificationName != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationNameID=" + notificationNameID +
                ", notificationName='" + notificationName + '\'' +
                '}';
    }
}
