package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.data.access.Connection;
import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Rating;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.data.repository.RatingRepository;
import KursovProektOOP2.data.services.RatingService;
import KursovProektOOP2.data.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AgentRating {

    @FXML
    public Text rateText;
    @FXML
    HBox starHbox;
    public int ownerID;
    public int agentID;

    public final UserService userService = UserService.getInstance();
    public final RatingService ratingService = RatingService.getInstance();

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
                Stage stage = (Stage) star.getScene().getWindow();
                Owner owner = userService.getOwnerByID(ownerID);
                Agent agent = userService.getAgentByID(agentID);

                ratingService.createOrUpdateRating(agent, owner, Double.parseDouble(star.getId()));
                stage.close();
            });
            Group root = new Group(star);
            starHbox.getChildren().add(root);
        }

    }
}
