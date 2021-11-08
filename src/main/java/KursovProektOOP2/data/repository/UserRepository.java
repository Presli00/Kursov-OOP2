package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.List;
import java.util.Optional;

public class UserRepository implements DAORepository {

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("User saved succsesfully");
        } catch (Exception ex) {
            log.error("User save error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public Optional getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }
}
