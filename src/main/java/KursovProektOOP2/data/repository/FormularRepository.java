package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Formular;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FormularRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(FormularRepository.class);

    private static FormularRepository getInstance(){
        return FormularRepository.FormularRepositoryHolder.INSTANCE;
    }

    private static class FormularRepositoryHolder{
        public static final FormularRepository INSTANCE = new FormularRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Formulars saved successfully");
        } catch (Exception ex) {
            log.error("Formulars save error" + ex.getMessage());
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
            log.info("Formulars updated successfully");
        } catch (Exception ex) {
            log.error("Formulars update error" + ex.getMessage());
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
            log.info("Formulars delete successfully");
        } catch (Exception ex) {
            log.error("Formulars delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Formular> foundFormular = null;
        try {
            foundFormular = session.byId(Formular.class).loadOptional(id);
            log.info("Formulars get by id successful");
        } catch (Exception ex) {
            log.error("Formulars get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundFormular;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Formular> formulars = new LinkedList<>();
        try{
            String jpql = "SELECT f FROM Formular f";
            formulars.addAll(session.createQuery(jpql, Formular.class).getResultList());
            log.info("Got all formulars");
        }catch(Exception ex){
            log.error("Get formulars failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return formulars;
    }
}
