package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.ProductType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductTypeRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(ProductTypeRepository.class);

    public static ProductTypeRepository getInstance(){
        return ProductTypeRepository.ProductTypeRepositoryHolder.INSTANCE;
    }

    private static class ProductTypeRepositoryHolder{
        public static final ProductTypeRepository INSTANCE = new ProductTypeRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("ProductType saved successfully");
        } catch (Exception ex) {
            log.error("ProductType save error" + ex.getMessage());
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
            log.info("ProductType updated successfully");
        } catch (Exception ex) {
            log.error("ProductType update error" + ex.getMessage());
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
            log.info("ProductType delete successfully");
        } catch (Exception ex) {
            log.error("ProductType delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<ProductType> foundProductType = null;
        try {
            foundProductType = session.byId(ProductType.class).loadOptional(id);
            log.info("ProductType get by id successful");
        } catch (Exception ex) {
            log.error("ProductType get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundProductType;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<ProductType> productTypes = new LinkedList<>();
        try{
            String jpql = "SELECT p FROM ProductType p";
            productTypes.addAll(session.createQuery(jpql, ProductType.class).getResultList());
            log.info("Got all Product Types");
        }catch(Exception ex){
            log.error("Get Product Types failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return productTypes;
    }
}
