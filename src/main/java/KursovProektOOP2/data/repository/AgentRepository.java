package KursovProektOOP2.data.repository;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Agent;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class AgentRepository implements DAORepository {
    private static final Logger log = Logger.getLogger(UserRepository.class);

    public static AgentRepository getInstance() {
        return AgentRepository.AgentRepositoryHolder.INSTANCE;
    }

    private static class AgentRepositoryHolder {
        public static final AgentRepository INSTANCE = new AgentRepository();
    }

    @Override
    public void save(Object obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obj);
            log.info("Agent saved successfully");
        } catch (Exception ex) {
            log.error("Agent save error" + ex.getMessage());
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
            log.info("Agent updated successfully");
        } catch (Exception ex) {
            log.error("Agent update error" + ex.getMessage());
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
            log.info("Agent delete successfully");
        } catch (Exception ex) {
            log.error("Agent delete error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional getById(int id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Agent> foundAgent = null;
        try {
            foundAgent = session.byId(Agent.class).loadOptional(id);
            log.info("Agent get by id successfully");
        } catch (Exception ex) {
            log.error("Agent get by id error" + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return foundAgent;
    }

    @Override
    public List getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Agent> agents = new LinkedList<>();
        try {
            String jpql = "SELECT a FROM Agent a";
            agents.addAll(session.createQuery(jpql, Agent.class).getResultList());
            log.info("Got all agents");
        } catch (Exception ex) {
            log.error("Get agents failed: " + ex.getMessage());
        } finally {
            transaction.commit();
        }
        return agents;
    }
}
