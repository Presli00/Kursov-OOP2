package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserRepository implements DAORepository {

    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static UserRepository getInstance(){
        return UserRepository.UserRepositoryHolder.INSTANCE;
    }

    private static class UserRepositoryHolder{
        public static final UserRepository INSTANCE = new UserRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("User saved successfully");
        } catch (Exception ex) {
            log.error("User save error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(obj);
            log.info("User updated successfully");
        } catch (Exception ex) {
            log.error("User update error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
            log.info("User delete successfully");
        } catch (Exception ex) {
            log.error("User delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<User> foundUser = null;
        try {
            foundUser = session.byId(User.class).loadOptional(id);
            log.info("User get by id successfully");
        } catch (Exception ex) {
            log.error("User get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundUser;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = new LinkedList<>();
        try{
            String jpql = "SELECT u FROM User u";
            users.addAll(session.createQuery(jpql, User.class).getResultList());
            log.info("Got all users");
        }catch(Exception ex){
            log.error("Get users failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return users;
    }
}
