package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.User;
import KursovProektOOP2.data.entity.WarehouseHasRooms;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class WarehouseHasRoomsRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(WarehouseHasRoomsRepository.class);

    private static WarehouseHasRoomsRepository getInstance(){
        return WarehouseHasRoomsRepository.WarehouseHasRoomsRepositoryHolder.INSTANCE;
    }

    private static class WarehouseHasRoomsRepositoryHolder{
        public static final WarehouseHasRoomsRepository INSTANCE = new WarehouseHasRoomsRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("WarehouseHasRooms saved successfully");
        } catch (Exception ex) {
            log.error("WarehouseHasRooms save error" + ex.getMessage());
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
            log.info("WarehouseHasRooms updated successfully");
        } catch (Exception ex) {
            log.error("WarehouseHasRooms update error" + ex.getMessage());
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
            log.info("WarehouseHasRooms delete successfully");
        } catch (Exception ex) {
            log.error("WarehouseHasRooms delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<WarehouseHasRooms> foundWarehouseHasRooms = null;
        try {
            foundWarehouseHasRooms = session.byId(WarehouseHasRooms.class).loadOptional(id);
            log.info("WarehouseHasRooms get by id successfully");
        } catch (Exception ex) {
            log.error("WarehouseHasRooms get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundWarehouseHasRooms;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<WarehouseHasRooms> warehouseHasRooms= new LinkedList<>();
        try{
            String jpql = "SELECT w FROM WarehouseHasRooms w";
            warehouseHasRooms.addAll(session.createQuery(jpql, WarehouseHasRooms.class).getResultList());
            log.info("Got all WarehouseHasRooms");
        }catch(Exception ex){
            log.error("Get WarehouseHasRooms failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return warehouseHasRooms;
    }
}
