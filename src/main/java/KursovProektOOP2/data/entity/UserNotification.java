package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "usernotifications", schema = "warehouse")
public class UserNotification implements Serializable {
    @Id
    @Column(name = "idnotifications", nullable = false)
    private int idNotifications;
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User idFromUser;
    @OneToMany
    @JoinColumn(name = "notificationNameID", nullable = false)
    private Set<Notification> Notifications;
    @Column(name = "isRead", nullable = false)
    private boolean isRead;

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

    public Set<Notification> getNotifications() {
        return Notifications;
    }

    public void setNotifications(Set<Notification> Notifications) { this.Notifications = Notifications; }

    public boolean isRead() { return isRead; }

    public void setRead(boolean read) { isRead = read; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserNotification that = (UserNotification) o;

        if (idNotifications != that.idNotifications) return false;
        if (idFromUser != null ? !idFromUser.equals(that.idFromUser) : that.idFromUser != null) return false;
        if (Notifications != null ? !Notifications.equals(that.Notifications) : that.Notifications != null)
            return false;
        if(isRead != that.isRead) return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserNotification{" +
                "idNotifications=" + idNotifications +
                ", idFromUser=" + idFromUser +
                ", Notifications=" + Notifications +
                ", isRead=" + isRead +
                '}';
    }
}