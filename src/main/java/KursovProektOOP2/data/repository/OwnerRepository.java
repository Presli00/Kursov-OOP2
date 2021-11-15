package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Owner;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OwnerRepository implements DAORepository {
    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static OwnerRepository getInstance() {
        return OwnerRepository.OwnerRepositoryHolder.INSTANCE;
    }

    private static class OwnerRepositoryHolder {
        public static final OwnerRepository INSTANCE = new OwnerRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Owner saved successfully");
        } catch (Exception ex) {
            log.error("Owner save error" + ex.getMessage());
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
            log.info("Owner updated successfully");
        } catch (Exception ex) {
            log.error("Owner update error" + ex.getMessage());
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
            log.info("Owner delete successfully");
        } catch (Exception ex) {
            log.error("Owner delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Owner> foundOwner = null;
        try {
            foundOwner = session.byId(Owner.class).loadOptional(id);
            log.info("Owner get by id successfully");
        } catch (Exception ex) {
            log.error("Owner get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundOwner;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Owner> owners = new LinkedList<>();
        try {
            String jpql = "SELECT o FROM Owner o";
            owners.addAll(session.createQuery(jpql, Owner.class).getResultList());
            log.info("Got all owners");
        } catch (Exception ex) {
            log.error("Get owners failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return owners;
    }
}
