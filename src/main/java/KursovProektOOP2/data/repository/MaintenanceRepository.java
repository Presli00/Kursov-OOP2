package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Maintenance;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MaintenanceRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(MaintenanceRepository.class);

    private static MaintenanceRepository getInstance(){
        return MaintenanceRepository.MaintenanceRepositoryHolder.INSTANCE;
    }

    private static class MaintenanceRepositoryHolder{
        public static final MaintenanceRepository INSTANCE = new MaintenanceRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Maintenance saved successfully");
        } catch (Exception ex) {
            log.error("Maintenance save error" + ex.getMessage());
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
            log.info("Maintenance updated successfully");
        } catch (Exception ex) {
            log.error("Maintenance update error" + ex.getMessage());
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
            log.info("Maintenance delete successfully");
        } catch (Exception ex) {
            log.error("Maintenance delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Maintenance> foundMaintenance = null;
        try {
            foundMaintenance = session.byId(Maintenance.class).loadOptional(id);
            log.info("Maintenance get by id successful");
        } catch (Exception ex) {
            log.error("Maintenance get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundMaintenance;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Maintenance> maintenance = new LinkedList<>();
        try{
            String jpql = "SELECT m FROM Maintenance m";
            maintenance.addAll(session.createQuery(jpql, Maintenance.class).getResultList());
            log.info("Got all maintenance");
        }catch(Exception ex){
            log.error("Get maintenance failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return maintenance;
    }
}
