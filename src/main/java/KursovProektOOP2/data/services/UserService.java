package KursovProektOOP2.data.services;

import KursovProektOOP2.controllers.Main;
import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.repository.UserRepository;
import KursovProektOOP2.util.UserSession;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Timestamp;
import java.util.Date;

public class UserService {
    public final UserRepository repository = UserRepository.getInstance();

    public static UserService getInstance(){
        return UserService.UserServiceHolder.INSTANCE;
    }

    private static class UserServiceHolder{
        public static final UserService INSTANCE = new UserService();
    }

    private static final Logger log = Logger.getLogger(Main.class);

    public void addUser(String username, String firstName, String lastName, String email, String phone, String pass, boolean passC, Role role){
        User user = new User();
        user.setUserId(0);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.seteMail(email);
        user.setPhone(phone);
        if(passC){
            user.setPassword(pass);
        }else{
            user.setPassword(pass);
        }
        user.setRoleId(role);
        Date date = new Date();
        user.setCreatedDate(new Timestamp(date.getTime()));
        user.setUpdatedDate(null);
        repository.save(user);
    }

    public void updateUser(String username, String pass){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String CHANGE_QUERY = "SELECT u FROM User u WHERE userId='" + UserSession.getUserID() + "'"; //query to change the username
        Date date=new Date();
        try {
            User result = (User) session.createQuery(CHANGE_QUERY).getSingleResult();
            result.setUsername(username);
            result.setPassword(pass);
            result.setUpdatedDate(new Timestamp(date.getTime()));
            UserSession.setUpdatedDate(new Timestamp(date.getTime()));
        } catch (Exception ex) {
            log.error("Password didn't match");
        } finally {
            transaction.commit();
        }
    }
}
