package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.controllers.Main;
import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Rating;
import KursovProektOOP2.data.entity.Usernotifications;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.data.repository.RatingRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;
import KursovProektOOP2.util.UserSession;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AgentRating {

    @FXML
    public Text rateText;
    @FXML
    HBox starHbox;
    public int ownerID;
    public int agentID;

    public final AgentRepository agentRepository = AgentRepository.getInstance();
    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();
    public final RatingRepository ratingRepository = RatingRepository.getInstance();
    private static final Logger log = Logger.getLogger(Main.class);

    @FXML
    private void initialize(){
        for (int i = 0; i < 5; i++) {
            Polygon star = new Polygon();
            star.getPoints().addAll(13.0, 18.0,
                    15.0, 10.0,
                    10.0, 5.0,
                    17.0, 5.0,
                    20.0, -2.0,
                    23.0, 5.0,
                    30.0, 5.0,
                    25.0, 10.0,
                    27.0, 18.0,
                    20.0, 12.5);
            star.setId(String.format("%s", i+1));
            star.getStyleClass().add("star");
            star.setOnMouseClicked(e->{
                Owner owner = (Owner) ownerRepository.getById(ownerID).get();
                Agent agent = (Agent) agentRepository.getById(agentID).get();

                Session session = Connection.openSession();
                Transaction transaction = session.beginTransaction();
                String RATING_QUERY = "SELECT u FROM Rating u WHERE idOwner = " + ownerID + " AND Agentobj = " + agentID;
                Rating rating = null;
                try {
                    rating = (Rating) session.createQuery(RATING_QUERY).getSingleResult();
                    if(rating == null){
                        rating = new Rating();
                    }
                    rating.setRating(Double.parseDouble(star.getId()));
                    rating.setIdAgent(agent);
                    rating.setIdOwner(owner);

                    ratingRepository.save(rating);
                } catch (Exception ex) {
                    log.error("Rating update unsuccessful " + "\n" + ex.getMessage());
                } finally {
                    transaction.commit();
                    session.close();
                }

            });
            Group root = new Group(star);
            starHbox.getChildren().add(root);
        }

    }
}
