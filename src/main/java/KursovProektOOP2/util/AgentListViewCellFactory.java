package KursovProektOOP2.util;

import KursovProektOOP2.data.entity.Agent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AgentListViewCellFactory implements Callback<ListView<Agent>, ListCell<Agent>> {
    @Override
    public ListCell<Agent> call(ListView<Agent> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Agent agent, boolean empty) {
                super.updateItem(agent, empty);
                if (empty || agent == null) {
                    setText(null);
                } else {
                    setText(agent.getIdFromUser().getUsername() + " | " + agent.getIdFromUser().getFirstName() + " " + agent.getIdFromUser().getLastName());
                }
            }
        };
    }
}
