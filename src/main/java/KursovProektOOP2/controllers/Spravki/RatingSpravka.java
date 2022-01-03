package KursovProektOOP2.controllers.Spravki;

import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Rating;
import KursovProektOOP2.data.services.UserService;
import KursovProektOOP2.models.AgentModel;
import KursovProektOOP2.util.Panes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class RatingSpravka {

    @FXML
    TableView<AgentModel> table;
    @FXML
    TableColumn number;
    @FXML
    TableColumn username;
    @FXML
    TableColumn firstName;
    @FXML
    TableColumn lastName;
    @FXML
    TableColumn email;
    @FXML
    TableColumn phone;
    @FXML
    TableColumn dealAmount;
    @FXML
    TableColumn rating;

    public final UserService userService = UserService.getInstance();

    @FXML
    private void initialize(){
        List<Agent> agentEntities = new ArrayList<>(userService.getAllAgents());
        ObservableList<AgentModel> agentModels = FXCollections.observableArrayList();
        for (int i = 0; i < agentEntities.size(); i++) {
            List<Rating> agentRating = new ArrayList<>(agentEntities.get(i).getReceivedRatings());
            agentModels.add(new AgentModel(i + 1,agentEntities.get(i).getIdFromUser().getUsername(),
                    agentEntities.get(i).getIdFromUser().getFirstName(),
                    agentEntities.get(i).getIdFromUser().getLastName(),
                    agentEntities.get(i).getIdFromUser().geteMail(),
                    agentEntities.get(i).getIdFromUser().getPhone(),
                    agentEntities.get(i).getDealAmount(),
                    agentEntities.get(i).getReceivedRatings().size() == 0 ? 0.0 : Panes.getRating(agentRating)));

        }
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        dealAmount.setCellValueFactory(new PropertyValueFactory<>("dealAmount"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        for (int i = 0; i < agentModels.size(); i++) {
            table.getItems().add(agentModels.get(i));
        }

    }
}
