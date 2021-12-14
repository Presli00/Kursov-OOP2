package KursovProektOOP2.util;

import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.Usernotifications;

import java.util.List;

public class UserSession {
    private static UserSession instance;
    private static int userID;
    private static String userName;
    private static String password;
    private static String first_name;
    private static String last_name;
    private static Role roleID;
    private static Owner owner;
    private static Agent agent;
    private static List<Usernotifications> notifications;
    public UserSession(int userID, String userName, String password, String first_name, String last_name, Role roleID) {
        this.userID=userID;
        this.userName = userName;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.roleID = roleID;
    }

    public static UserSession getInstance(int userID, String userName, String password, String first_name, String last_name, Role roleID) {
        if(instance == null) {
            instance = new UserSession(userID, userName, password, first_name,last_name, roleID);
        }
        return instance;
    }

    public static UserSession getInstance(){ return instance;}

    public static int getUserID() {
        return userID;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getpassword() {
        return password;
    }

    public static String getFirst_name() {
        return first_name;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static List<Usernotifications> getNotifications() {
        return notifications;
    }

    public static void setOwnerObject(Owner owner) {
        UserSession.owner = owner;
    }

    public static void setAgentObject(Agent agent) {
        UserSession.agent = agent;
    }

    public static Owner getOwnerObject() {
        return owner;
    }

    public static Agent getAgentObject() {
        return agent;
    }

    public static void setNotifications(List<Usernotifications> notifications) {
        UserSession.notifications = notifications;
    }

    public static Role getRoleID() {
        return roleID;
    }

    public static void cleanUserSession() {
        instance = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges='" + password + '\'' +
                ", userID=" + userID +
                ", firstName="+first_name+
                ", lastName="+last_name+
                '}';
    }
}