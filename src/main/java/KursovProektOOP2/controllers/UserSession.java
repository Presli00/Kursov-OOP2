package KursovProektOOP2.controllers;

public class UserSession {
    private static UserSession instance;
    private static int userID;
    private static String userName;
    private static String password;
    public UserSession(int userID, String userName, String password) {
        this.userID=userID;
        this.userName = userName;
        this.password = password;
    }

    public static UserSession getInstance(int userID, String userName, String password) {
        if(instance == null) {
            instance = new UserSession(userID, userName, password);
        }
        return instance;
    }


    public static int getUserID() {
        return userID;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getpassword() {
        return password;
    }

    public static void cleanUserSession() {
        userID=0;
        userName = "";// or null
        password = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges='" + password + '\'' +
                ", userID=" + userID +
                '}';
    }
}
