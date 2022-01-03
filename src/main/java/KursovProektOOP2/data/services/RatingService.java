package KursovProektOOP2.data.services;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Rating;
import KursovProektOOP2.data.repository.RatingRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RatingService {
    public final RatingRepository repository = RatingRepository.getInstance();

    public static RatingService getInstance(){
        return RatingService.RatingServiceHolder.INSTANCE;
    }

    private static class RatingServiceHolder{
        public static final RatingService INSTANCE = new RatingService();
    }

    public void createOrUpdateRating(Agent agent, Owner owner, double ratedAt){
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        String RATING_QUERY = "SELECT u FROM Rating u WHERE idOwner = " + owner.getIdOwner() + " AND Agentobj = " + agent.getIdAgent();
        Rating rating;
        try {
            rating = (Rating) session.createQuery(RATING_QUERY).getSingleResult();
        } catch (Exception ex) {
            rating = new Rating();
        } finally {
            transaction.commit();
            session.close();
        }
        rating.setRating(ratedAt);
        rating.setIdAgent(agent);
        rating.setIdOwner(owner);
        repository.save(rating);
    }
}
