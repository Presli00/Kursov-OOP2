package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RoleRepository implements DAORepository {

    private static final Logger log = Logger.getLogger(RoleRepository.class);

    private static RoleRepository getInstance() {
        return RoleRepository.RoleRepositoryHolder.INSTANCE;
    }

    private static class RoleRepositoryHolder {
        public static final RoleRepository INSTANCE = new RoleRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Role saved successfully");
        } catch (Exception ex) {
            log.error("Role save error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("Role updated successfully");
        } catch (Exception ex) {
            log.error("Role update error" + ex.getMessage());
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
            log.info("Role deleted successfully");
        } catch (Exception ex) {
            log.error("Role delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        Optional<Role> foundRole=null;
        try {
            foundRole = session.byId(Role.class).loadOptional(id);
            log.info("Role get by id successfully");
        } catch (Exception ex) {
            log.error("Role get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundRole;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Role> roles = new LinkedList<>();
        try{
            String jpql = "SELECT r FROM Role r";
            roles.addAll(session.createQuery(jpql, Role.class).getResultList());
            log.info("Got all roles");
        }catch(Exception ex){
            log.error("Get roles failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return roles;
    }
}
