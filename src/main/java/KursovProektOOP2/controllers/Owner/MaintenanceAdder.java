package KursovProektOOP2.controllers.Owner;
import KursovProektOOP2.data.entity.Maintenance;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.MaintenanceRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;
import KursovProektOOP2.util.MaintenanceListViewCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.util.List;

public class MaintenanceAdder {
    @FXML
    ComboBox availableMaintenanceBox;
    @FXML
    ListView currentMaintenanceList;
    @FXML
    Text errorText;
    private int warehouseID;
    Warehouse warehouse;
    public final MaintenanceRepository maintenanceRepository=MaintenanceRepository.getInstance();
    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();@FXML
    private void initialize(){
        new Thread(() -> { // OPEN NEW THREAD AND WAIT FOR WAREHOUSEINFO TO SET WAREHOUSE ID
            try {
                Thread.sleep(100); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse = (Warehouse)warehouseRepository.getById(warehouseID).get();
            List<Maintenance> allMaintenance = maintenanceRepository.getAll();
            Maintenance currentMaintenance = warehouse.getMaintenanceId();
            availableMaintenanceBox.setCellFactory(new MaintenanceListViewCellFactory());
            currentMaintenanceList.setCellFactory(new MaintenanceListViewCellFactory());
            for(int i=0;i<allMaintenance.size();i++){
                availableMaintenanceBox.getItems().add(allMaintenance.get(i));
            }
                currentMaintenanceList.getItems().add(currentMaintenance);
            /*for(int i=0;i<allMaintenance.size();i++){
                if(warehouse.getMaintenanceId().isEmployed()){
                    allMaintenance.remove(i);
                }
            }*/
            /*for(int i = 0; i < 1; i++){
                Maintenance current = currentMaintenance;
                for(int j = 0; j < allMaintenance.size(); j++){
                    if(allMaintenance.get(j).getMaintenanceId()==current.getMaintenanceId()){
                        allMaintenance.remove(j); // remove agents that are already added to this warehouse
                    }
                }
            }
            availableMaintenanceBox.setCellFactory(new AgentListViewCellFactory());
            currentMaintenanceList.setCellFactory(new AgentListViewCellFactory());
            for (int i = 0; i < allMaintenance.size(); i++){
                availableMaintenanceBox.getItems().add(allMaintenance.get(i));
            }

            for (int i = 0; i < 1; i++){
                currentMaintenanceList.getItems().add(currentMaintenance);
            }*/

        }).start();


    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    @FXML
    private void addMaintenance(){
        if(currentMaintenanceList.getItems().size()<1) {
            Maintenance maintenance = (Maintenance) availableMaintenanceBox.getSelectionModel().getSelectedItem();
            availableMaintenanceBox.getItems().remove(maintenance);
            currentMaintenanceList.getItems().add(maintenance);
            maintenance.setEmployed(true);
        }
        else{
            errorText.setText("Може да има само един човек от поддръжка в склад");
        }
    }

    @FXML
    private void removeMaintenance(){
        Maintenance maintenance = (Maintenance) currentMaintenanceList.getSelectionModel().getSelectedItem();
        currentMaintenanceList.getItems().remove(maintenance);
        availableMaintenanceBox.getItems().add(maintenance);
        maintenance.setEmployed(false);
    }

    @FXML
    private void saveMaintenance(){
        for(int i = 0; i < currentMaintenanceList.getItems().size(); i++){
            Maintenance maintenance = (Maintenance) maintenanceRepository.getById(((Maintenance) currentMaintenanceList.getItems().get(i)).getMaintenanceId()).get();
            warehouse.setMaintenanceId(maintenance);
            maintenanceRepository.update(maintenance);
        }
        warehouseRepository.update(warehouse);
    }
}
