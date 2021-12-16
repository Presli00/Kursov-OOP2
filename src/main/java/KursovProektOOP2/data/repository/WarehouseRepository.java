package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.Warehouse;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class WarehouseRepository implements DAORepository{

    private static final Logger log = Logger.getLogger(WarehouseRepository.class);

    public static WarehouseRepository getInstance(){
        return WarehouseRepository.WarehouseRepositoryHolder.INSTANCE;
    }

    private static class WarehouseRepositoryHolder{
        public static final WarehouseRepository INSTANCE = new WarehouseRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Warehouse saved successfully");
        } catch (Exception ex) {
            log.error("Warehouse save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.merge(obj);
        }
    }

    @Override
    public void update(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(obj);
            log.info("Warehouse updated successfully");
        } catch (Exception ex) {
            log.error("Warehouse update error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.merge(obj);
        }
    }

    public void merge(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(obj);
            log.info("Warehouse merged successfully");
        } catch (Exception ex) {
            log.error("Warehouse merge error" + ex.getMessage());
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
            log.info("Warehouse delete successfully");
        } catch (Exception ex) {
            log.error("Warehouse delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Warehouse> foundWarehouse = null;
        try {
            foundWarehouse = session.byId(Warehouse.class).loadOptional(id);
            log.info("Warehouse get by id successfully");
        } catch (Exception ex) {
            log.error("Warehouse get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundWarehouse;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Warehouse> warehouses = new LinkedList<>();
        try{
            String jpql = "SELECT w FROM Warehouse w";
            warehouses.addAll(session.createQuery(jpql, Warehouse.class).getResultList());
            log.info("Got all warehouses");
        }catch(Exception ex){
            log.error("Get warehouses failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return warehouses;
    }
}
