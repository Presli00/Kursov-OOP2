package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.StorageRoom;
import KursovProektOOP2.data.entity.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StorageRoomRepository implements DAORepository{

    private static final Logger log = Logger.getLogger(StorageRoomRepository.class);

    private static StorageRoomRepository getInstance(){
        return StorageRoomRepository.StorageRoomRepositoryHolder.INSTANCE;
    }

    private static class StorageRoomRepositoryHolder{
        public static final StorageRoomRepository INSTANCE = new StorageRoomRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Storage room saved successfully");
        } catch (Exception ex) {
            log.error("Storage room save error" + ex.getMessage());
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
            log.info("Storage room updated successfully");
        } catch (Exception ex) {
            log.error("Storage room update error" + ex.getMessage());
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
            log.info("Storage room delete successfully");
        } catch (Exception ex) {
            log.error("Storage room delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<StorageRoom> foundStorageRoom = null;
        try {
            foundStorageRoom = session.byId(StorageRoom.class).loadOptional(id);
            log.info("Storage room get by id successfully");
        } catch (Exception ex) {
            log.error("Storage room get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundStorageRoom;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<StorageRoom> storageRooms = new LinkedList<>();
        try{
            String jpql = "SELECT s FROM StorageRoom s";
            storageRooms.addAll(session.createQuery(jpql, StorageRoom.class).getResultList());
            log.info("Got all storage rooms");
        }catch(Exception ex){
            log.error("Get storage rooms failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return storageRooms;
    }
}
