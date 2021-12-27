package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.RenterInformation;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RenterInformationRepository implements DAORepository {

    private static final Logger log = Logger.getLogger(RenterInformationRepository.class);

    public static RenterInformationRepository getInstance() {
        return RenterInformationRepository.RenterInformationRepositoryHolder.INSTANCE;
    }

    private static class RenterInformationRepositoryHolder {
        public static final RenterInformationRepository INSTANCE = new RenterInformationRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Renter saved successfully");
        } catch (Exception ex) {
            log.error("Renter save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public int saveAndReturnID(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        int id = 0;
        try {
            id = (Integer) session.save(obj);
            log.info("Renter saved successfully");
        } catch (Exception ex) {
            log.error("Renter save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
        return id;
    }

    @Override
    public void update(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("Renter updated successfully");
        } catch (Exception ex) {
            log.error("Renter update error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.save(obj);
            log.info("Renter deleted successfully");
        } catch (Exception ex) {
            log.error("Renter delete error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Optional getById(int id) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        Optional<RenterInformation> foundRenter = null;
        try {
            foundRenter = session.byId(RenterInformation.class).loadOptional(id);
            log.info("Renter get by id successful");
        } catch (Exception ex) {
            log.error("Renter get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
        return foundRenter;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<RenterInformation> renters = new LinkedList<>();
        try{
            String jpql = "SELECT r FROM RenterInformation r";
            renters.addAll(session.createQuery(jpql, RenterInformation.class).getResultList());
            log.info("Got all renters");
        }catch(Exception ex){
            log.error("Get renters failed: " + ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
        }
        return renters;
    }
}
