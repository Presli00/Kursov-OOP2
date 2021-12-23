package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Usernotifications;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserNotificationRepository implements DAORepository {

    private static final Logger log = Logger.getLogger(UserNotificationRepository.class);

    public static UserNotificationRepository getInstance() {
        return UserNotificationRepository.UserNotificationRepositoryHolder.INSTANCE;
    }

    private static class UserNotificationRepositoryHolder {
        public static final UserNotificationRepository INSTANCE = new UserNotificationRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("UserNotification saved successfully");
        } catch (Exception ex) {
            log.error("UserNotification save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("UserNotification updated successfully");
        } catch (Exception ex) {
            log.error("UserNotification update error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("UserNotification deleted successfully");
        } catch (Exception ex) {
            log.error("UserNotification delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(int id) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        Optional<Usernotifications> foundUserNotification=null;
        try {
            foundUserNotification = session.byId(Usernotifications.class).loadOptional(id);
            log.info("UserNotification get by id successfully");
        } catch (Exception ex) {
            log.error("UserNotification get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundUserNotification;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Usernotifications> UserNotifications = new LinkedList<>();
        try{
            String jpql = "SELECT u FROM Usernotifications u";
            UserNotifications.addAll(session.createQuery(jpql, Usernotifications.class).getResultList());
            log.info("Got all UserNotifications");
        }catch(Exception ex){
            log.error("Get UserNotifications failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return UserNotifications;
    }
}
