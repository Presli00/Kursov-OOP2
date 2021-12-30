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
import javafx.util.StringConverter;

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
    public final MaintenanceRepository maintenanceRepository = MaintenanceRepository.getInstance();
    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();

    @FXML
    private void initialize() {
        new Thread(() -> { // OPEN NEW THREAD AND WAIT FOR WAREHOUSEINFO TO SET WAREHOUSE ID
            try {
                Thread.sleep(100); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse = (Warehouse) warehouseRepository.getById(warehouseID).get();
            List<Maintenance> allMaintenance = maintenanceRepository.getAll();
            Maintenance currentMaintenance = warehouse.getMaintenanceId();
            for (int i = 0; i < allMaintenance.size(); i++) {
                    if (allMaintenance.get(i).isEmployed()) {
                        allMaintenance.remove(i);
                    }
            }
            for (int i = 0; i < allMaintenance.size(); i++) {
                availableMaintenanceBox.getItems().add(allMaintenance.get(i));
            }
            availableMaintenanceBox.setConverter(new StringConverter<Maintenance>() {
                @Override
                public String toString(Maintenance maintenance) {
                    return maintenance.getName();
                }

                @Override
                public Maintenance fromString(final String string) {
                    return (Maintenance) availableMaintenanceBox.getItems().stream().filter(maintenance -> ((Maintenance) maintenance).getName().equals(string)).findFirst().orElse(null);
                }
            });
            currentMaintenanceList.setCellFactory(new MaintenanceListViewCellFactory());
            if(currentMaintenance != null){
                currentMaintenanceList.getItems().add(currentMaintenance);
            }
        }).start();


    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    @FXML
    private void addMaintenance() {
        if (currentMaintenanceList.getItems().size() < 1) {
            currentMaintenanceList.refresh();
            Maintenance maintenance = (Maintenance) availableMaintenanceBox.getSelectionModel().getSelectedItem();
            availableMaintenanceBox.getItems().remove(maintenance);
            currentMaintenanceList.getItems().add(maintenance);
            maintenance.setEmployed(true);
        } else {
            errorText.setText("Може да има само един човек от поддръжка в склад");
        }
    }
    // && !currentMaintenanceList.getSelectionModel().isEmpty()
    @FXML
    private void removeMaintenance() {
        if(!currentMaintenanceList.getItems().isEmpty()){
            currentMaintenanceList.getSelectionModel().selectFirst();
            Maintenance maintenance = (Maintenance) currentMaintenanceList.getSelectionModel().getSelectedItem();
            currentMaintenanceList.getItems().remove(maintenance);
            availableMaintenanceBox.getItems().add(maintenance);
            maintenance.setEmployed(false);
        }
        else{
            errorText.setText("Няма хора за премахване");
        }
    }

    @FXML
    private void saveMaintenance() {
        for (int i = 0; i < currentMaintenanceList.getItems().size(); i++) {
            Maintenance maintenance = (Maintenance) maintenanceRepository.getById(((Maintenance) currentMaintenanceList.getItems().get(i)).getMaintenanceId()).get();
            warehouse.setMaintenanceId(maintenance);
            warehouse.getMaintenanceId().setEmployed(true);
            maintenanceRepository.update(maintenance);
        }
        warehouseRepository.update(warehouse);
    }
}