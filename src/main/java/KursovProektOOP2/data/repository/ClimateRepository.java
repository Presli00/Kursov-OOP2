package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Climate;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ClimateRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(ClimateRepository.class);

    private static ClimateRepository getInstance(){
        return ClimateRepository.ClimateRepositoryHolder.INSTANCE;
    }

    private static class ClimateRepositoryHolder{
        public static final ClimateRepository INSTANCE = new ClimateRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Climate saved successfully");
        } catch (Exception ex) {
            log.error("Climate save error" + ex.getMessage());
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
            log.info("Climate updated successfully");
        } catch (Exception ex) {
            log.error("Climate update error" + ex.getMessage());
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
            log.info("Climate delete successful");
        } catch (Exception ex) {
            log.error("Climate delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Climate> Climate = null;
        try {
            Climate = session.byId(Climate.class).loadOptional(id);
            log.info("Climate get by id successfully");
        } catch (Exception ex) {
            log.error("Climate get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return Climate;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Climate> climates = new LinkedList<>();
        try{
            String jpql = "SELECT c FROM Climate c";
            climates.addAll(session.createQuery(jpql, Climate.class).getResultList());
            log.info("Got all climates");
        }catch(Exception ex){
            log.error("Get climates failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return climates;
    }
}
