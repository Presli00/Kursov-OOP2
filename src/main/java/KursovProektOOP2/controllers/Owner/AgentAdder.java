package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.services.UserNotificationService;
import KursovProektOOP2.data.services.UserService;
import KursovProektOOP2.data.services.WarehouseService;
import KursovProektOOP2.util.AgentListViewCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class AgentAdder {
    @FXML
    ListView availableAgentsList;
    @FXML
    ListView currentAgentsList;
    private int warehouseID;
    Warehouse warehouse;

    public final UserService userService = UserService.getInstance();
    public final WarehouseService warehouseService = WarehouseService.getInstance();
    public final UserNotificationService userNotificationService = UserNotificationService.getInstance();

    @FXML
    private void initialize(){
        new Thread(() -> { // OPEN NEW THREAD AND WAIT FOR WAREHOUSEINFO TO SET WAREHOUSE ID
            try {
                Thread.sleep(100); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse = warehouseService.getWarehouseByID(warehouseID);
            List<Agent> allAgents = userService.getAllAgents();
            List<Agent> currentAgents = new ArrayList<>(warehouse.getAgentsId());
            for(int i = 0; i < currentAgents.size(); i++){
                Agent current = currentAgents.get(i);
                for(int j = 0; j < allAgents.size(); j++){
                    if(allAgents.get(j).getIdFromUser().getUserId() == current.getIdFromUser().getUserId()){
                        allAgents.remove(j); // remove agents that are already added to this warehouse
                    }
                }
            }

            availableAgentsList.setCellFactory(new AgentListViewCellFactory());
            currentAgentsList.setCellFactory(new AgentListViewCellFactory());
            for (int i = 0; i < allAgents.size(); i++){
                availableAgentsList.getItems().add(allAgents.get(i));
            }

            for (int i = 0; i < currentAgents.size(); i++){
                currentAgentsList.getItems().add(currentAgents.get(i));
            }

        }).start();


    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    @FXML
    private void addAgent(){
        Agent agent = (Agent) availableAgentsList.getSelectionModel().getSelectedItem();
        availableAgentsList.getItems().remove(agent);
        currentAgentsList.getItems().add(agent);
    }

    @FXML
    private void removeAgent(){
        Agent agent = (Agent) currentAgentsList.getSelectionModel().getSelectedItem();
        currentAgentsList.getItems().remove(agent);
        availableAgentsList.getItems().add(agent);
    }

    @FXML
    private void saveAgents(){
        warehouse.getAgentsId().clear(); // clear so we don't have duplicates or old values
        for(int i = 0; i < currentAgentsList.getItems().size(); i++){
            Agent agent = userService.getAgentByID(((Agent) currentAgentsList.getItems().get(i)).getIdAgent());
            warehouse.getAgentsId().add(agent);
            userNotificationService.createAgentNotif(agent, warehouse);
        }
        warehouseService.updateWarehouse(warehouse);
    }
}
