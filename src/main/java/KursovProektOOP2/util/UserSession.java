package KursovProektOOP2.util;

import KursovProektOOP2.data.entity.*;

import java.util.List;

public class UserSession {
    private static User user;
    private static UserSession instance;
    private static Owner owner;
    private static Agent agent;
    private static List<Usernotifications> notifications;
    public UserSession(User user) {
        this.user = user;
    }

    public static UserSession getInstance(User user) {
        if(instance == null) {
            instance = new UserSession(user);
        }
        return instance;
    }

    public static UserSession getInstance(){ return instance;}

    public static int getUserID() {
        return user.getUserId();
    }

    public static String getUserName() {
        return user.getUsername();
    }

    public static Owner getOwner() {
        return owner;
    }

    public static void setOwner(Owner owner) {
        UserSession.owner = owner;
    }

    public static Agent getAgent() {
        return agent;
    }

    public static void setAgent(Agent agent) {
        UserSession.agent = agent;
    }

    public static String getpassword() {
        return user.getPassword();
    }

    public static String getFirst_name() {
        return user.getFirstName();
    }

    public static String getLast_name() {
        return user.getLastName();
    }

    public static void setNotifications(List<Usernotifications> notifications) {
        UserSession.notifications = notifications;
    }

    public static List<Usernotifications> getNotifications() {
        return notifications;
    }

    public static Role getRoleID() {
        return user.getRoleId();
    }

    public static void cleanUserSession() {
        instance = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + user.getUsername() + '\'' +
                ", privileges='" + user.getPassword() + '\'' +
                ", userID=" + user.getUserId() +
                ", firstName="+user.getFirstName()+
                ", lastName="+user.getLastName()+
                '}';
    }
}
