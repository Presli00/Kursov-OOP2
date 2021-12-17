package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.City;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityRepository implements DAORepository {
    private static final Logger log = Logger.getLogger(CityRepository.class);

    public static CityRepository getInstance(){
        return CityRepository.CityRepositoryHolder.INSTANCE;
    }

    private static class CityRepositoryHolder{
        public static final CityRepository INSTANCE = new CityRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("City saved successfully");
        } catch (Exception ex) {
            log.error("City save error" + ex.getMessage());
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
            log.info("City updated successfully");
        } catch (Exception ex) {
            log.error("City update error" + ex.getMessage());
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
            log.info("City delete successfully");
        } catch (Exception ex) {
            log.error("City delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<City> foundCity = null;
        try {
            foundCity = session.byId(City.class).loadOptional(id);
            log.info("City get by id successful");
        } catch (Exception ex) {
            log.error("City get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundCity;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<City> cities = new LinkedList<>();
        try{
            String jpql = "SELECT c FROM City c";
            cities.addAll(session.createQuery(jpql, City.class).getResultList());
            log.info("Got all cities");
        }catch(Exception ex){
            log.error("Get cities failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return cities;
    }
}
