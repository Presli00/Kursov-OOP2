package KursovProektOOP2.controllers;

public class UserSession {
    private static UserSession instance;
    private static int userID;
    private static String userName;
    private static String password;
    private static String first_name;
    private static String last_name;
    public UserSession(int userID, String userName, String password, String first_name, String last_name) {
        this.userID=userID;
        this.userName = userName;
        this.password = password;
        this.first_name=first_name;
        this.last_name=last_name;
    }

    public static UserSession getInstance(int userID, String userName, String password, String first_name, String last_name) {
        if(instance == null) {
            instance = new UserSession(userID, userName, password, first_name,last_name);
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

    public static void cleanUserSession() {
        userID=0;
        userName = "";// or null
        password = "";// or null
        first_name="";
        last_name="";
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
