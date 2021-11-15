package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.OwnerHasWarehouses;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OwnerHasWarehousesRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(OwnerHasWarehousesRepository.class);

    public static OwnerHasWarehousesRepository getInstance(){
        return OwnerHasWarehousesRepository.OwnerHasWarehousesRepositoryHolder.INSTANCE;
    }

    private static class OwnerHasWarehousesRepositoryHolder{
        public static final OwnerHasWarehousesRepository INSTANCE = new OwnerHasWarehousesRepository();
    }
    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("OwnerHasWarehouses saved successfully");
        } catch (Exception ex) {
            log.error("OwnerHasWarehouses save error" + ex.getMessage());
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
            log.info("OwnerHasWarehouses updated successfully");
        } catch (Exception ex) {
            log.error("OwnerHasWarehouses update error" + ex.getMessage());
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
            log.info("OwnerHasWarehouses delete successfully");
        } catch (Exception ex) {
            log.error("OwnerHasWarehouses delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<OwnerHasWarehouses> foundOwnerHasWarehouses = null;
        try {
            foundOwnerHasWarehouses = session.byId(OwnerHasWarehouses.class).loadOptional(id);
            log.info("OwnerHasWarehouses get by id successfully");
        } catch (Exception ex) {
            log.error("OwnerHasWarehouses get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundOwnerHasWarehouses;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<OwnerHasWarehouses> OwnerHasWarehouses = new LinkedList<>();
        try{
            String jpql = "SELECT ow FROM OwnerHasWarehouses ow";
            OwnerHasWarehouses.addAll(session.createQuery(jpql, OwnerHasWarehouses.class).getResultList());
            log.info("Got all OwnerHasWarehousess");
        }catch(Exception ex){
            log.error("Get OwnerHasWarehousess failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return OwnerHasWarehouses;
    }
}
