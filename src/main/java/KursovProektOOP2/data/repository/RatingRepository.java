package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Rating;
import KursovProektOOP2.data.entity.Role;
import KursovProektOOP2.data.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RatingRepository implements DAORepository {

    private static final Logger log = Logger.getLogger(RatingRepository.class);

    public static RatingRepository getInstance() {
        return RatingRepository.RatingRepositoryHolder.INSTANCE;
    }

    private static class RatingRepositoryHolder {
        public static final RatingRepository INSTANCE = new RatingRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(obj);
            log.info("Rating saved successfully");
        } catch (Exception ex) {
            log.error("Rating save error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.update(obj);
            log.info("Rating updated successfully");
        } catch (Exception ex) {
            log.error("Rating update error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Object obj) {
        Session session=Connection.openSession();
        Transaction transaction =session.beginTransaction();
        try {
            session.delete(obj);
            log.info("Rating deleted successfully");
        } catch (Exception ex) {
            log.error("Rating delete error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Optional getById(int id) {
        Session session=Connection.openSession();
        Transaction transaction=session.beginTransaction();
        Optional<Rating> foundRating=null;
        try {
            foundRating = session.byId(Rating.class).loadOptional(id);
            log.info("Rating get by id successfully");
        } catch (Exception ex) {
            log.error("Rating get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
            session.close();
        }
        return foundRating;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Rating> ratings = new LinkedList<>();
        try{
            String jpql = "SELECT r FROM Rating r";
            ratings.addAll(session.createQuery(jpql, Rating.class).getResultList());
            log.info("Got all ratings");
        }catch(Exception ex){
            log.error("Get ratings failed: " + ex.getMessage());
        }finally {
            transaction.commit();
            session.close();
        }
        return ratings;
    }
}
