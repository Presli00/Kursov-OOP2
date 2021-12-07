package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "usernotifications", schema = "warehouse")
@Entity
public class Usernotifications implements Serializable {
    @Id
    @Column(name = "idnotifications", nullable = false)
    private int idNotifications;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User idFromUser;
    @Column(name = "notificationName", nullable = false)
    private String notificationName;
    @Column(name = "isRead", nullable = false)
    private boolean isRead;
    @Column(name = "notifTimeStamp")
    private Timestamp notifTimeStamp;

    public int getIdNotifications() {
        return idNotifications;
    }

    public void setIdNotifications(int idNotifications) {
        this.idNotifications = idNotifications;
    }

    public User getIdFromUser() {
        return idFromUser;
    }

    public void setIdFromUser(User idFromUser) {
        this.idFromUser = idFromUser;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) { this.notificationName = notificationName; }

    public boolean isRead() { return isRead; }

    public void setRead(boolean read) { isRead = read; }

    public Timestamp getNotifTimeStamp() {
        return notifTimeStamp;
    }

    public void setNotifTimeStamp(Timestamp notifTimeStamp) {
        this.notifTimeStamp = notifTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usernotifications that = (Usernotifications) o;

        if (idNotifications != that.idNotifications) return false;
        if (idFromUser != null ? !idFromUser.equals(that.idFromUser) : that.idFromUser != null) return false;
        if (notificationName != null ? !notificationName.equals(that.notificationName) : that.notificationName != null)
            return false;
        if(isRead != that.isRead) return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserNotification{" +
                "idNotifications=" + idNotifications +
                ", idFromUser=" + idFromUser +
                ", NotificationName=" + notificationName +
                ", isRead=" + isRead +
                '}';
    }
}