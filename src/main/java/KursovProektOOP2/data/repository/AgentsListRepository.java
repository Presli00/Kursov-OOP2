package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AgentsListRepository implements DAORepository{
    private static final Logger log = Logger.getLogger(AgentsListRepository.class);

    private static AgentsListRepository getInstance(){
        return AgentsListRepository.AgentsListRepositoryHolder.INSTANCE;
    }

    private static class AgentsListRepositoryHolder{
        public static final AgentsListRepository INSTANCE = new AgentsListRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("AgentsLists saved successfully");
        } catch (Exception ex) {
            log.error("AgentsLists save error" + ex.getMessage());
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
            log.info("AgentsLists updated successfully");
        } catch (Exception ex) {
            log.error("AgentsLists update error" + ex.getMessage());
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
            log.info("AgentsLists delete successfully");
        } catch (Exception ex) {
            log.error("AgentsLists delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<AgentsListRepository> foundAgentsLists = null;
        try {
            foundAgentsLists = session.byId(AgentsListRepository.class).loadOptional(id);
            log.info("AgentsLists get by id successfully");
        } catch (Exception ex) {
            log.error("AgentsLists get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundAgentsLists;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<AgentsListRepository> agentsLists = new LinkedList<>();
        try{
            String jpql = "SELECT a FROM AgentsList a";
            agentsLists.addAll(session.createQuery(jpql, AgentsListRepository.class).getResultList());
            log.info("Got all AgentsLists");
        }catch(Exception ex){
            log.error("Get AgentsLists failed: " + ex.getMessage());
        }finally {
            transaction.commit();
        }
        return agentsLists;
    }
}
