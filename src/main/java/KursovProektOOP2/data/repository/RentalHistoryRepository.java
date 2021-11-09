package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.RentalHistory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RentalHistoryRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(RentalHistoryRepository.class);

    private static RentalHistoryRepository getInstance(){
        return RentalHistoryRepository.RentalHistoryRepositoryHolder.INSTANCE;
    }

    private static class RentalHistoryRepositoryHolder{
        public static final RentalHistoryRepository INSTANCE = new RentalHistoryRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Rental history saved successfully");
        } catch (Exception ex) {
            log.error("Rental history save error" + ex.getMessage());
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
            log.info("Rental history updated successfully");
        } catch (Exception ex) {
            log.error("Rental history update error" + ex.getMessage());
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
            log.info("Rental history delete successful");
        } catch (Exception ex) {
            log.error("Rental history delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<RentalHistory> foundRentalHistory = null;
        try {
            foundRentalHistory = session.byId(RentalHistory.class).loadOptional(id);
            log.info("Rental history get by id successful");
        } catch (Exception ex) {
            log.error("Rental history get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundRentalHistory;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<RentalHistory> rentalHistories = new LinkedList<>();
        try{
            String jpql = "SELECT u FROM RentalHistory u";
            rentalHistories.addAll(session.createQuery(jpql, RentalHistory.class).getResultList());
            log.info("Got all Rental histories");
        }catch(Exception ex){
            log.error("Get Rental histories failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return rentalHistories;
    }
}
