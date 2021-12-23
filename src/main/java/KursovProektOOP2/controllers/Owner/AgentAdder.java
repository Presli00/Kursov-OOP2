package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Usernotifications;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.UserNotificationRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;
import KursovProektOOP2.util.AgentListViewCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AgentAdder {
    @FXML
    ListView availableAgentsList;
    @FXML
    ListView currentAgentsList;
    private int warehouseID;
    Warehouse warehouse;

    public final AgentRepository agentRepository = AgentRepository.getInstance();
    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();
    public final UserNotificationRepository userNotificationRepository = UserNotificationRepository.getInstance();

    @FXML
    private void initialize(){
        new Thread(() -> { // OPEN NEW THREAD AND WAIT FOR WAREHOUSEINFO TO SET WAREHOUSE ID
            try {
                Thread.sleep(100); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse = (Warehouse)warehouseRepository.getById(warehouseID).get();
            List<Agent> allAgents = agentRepository.getAll();
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
            Agent agent = (Agent) agentRepository.getById(((Agent) currentAgentsList.getItems().get(i)).getIdAgent()).get();
            warehouse.getAgentsId().add(agent);
            Usernotifications userNotif = new Usernotifications();
            userNotif.setIdFromUser(agent.getIdFromUser());
            userNotif.setNotificationName("Бяхте добавен като складов агент за  склад: " + warehouse.getWarehouseName() + "\nТой принадлежи на " + warehouse.getOwnerId().getUserId().getUsername());
            userNotif.setNotifTimeStamp(new Timestamp(System.currentTimeMillis()));
            agent.getIdFromUser().getUsernotifications().add(userNotif);
            agentRepository.update(agent);
            userNotificationRepository.save(userNotif);
        }
        warehouseRepository.update(warehouse);
    }
}
