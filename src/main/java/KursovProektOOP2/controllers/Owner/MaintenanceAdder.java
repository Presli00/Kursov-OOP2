package KursovProektOOP2.controllers.Owner;

import KursovProektOOP2.data.entity.Maintenance;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.services.WarehouseService;
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
    public final WarehouseService warehouseService = WarehouseService.getInstance();

    @FXML
    private void initialize() {
        new Thread(() -> { // OPEN NEW THREAD AND WAIT FOR WAREHOUSEINFO TO SET WAREHOUSE ID
            try {
                Thread.sleep(100); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            warehouse = warehouseService.getWarehouseByID(warehouseID);
            List<Maintenance> allMaintenance = warehouseService.getAllMaintenance();
            Maintenance currentMaintenance = warehouse.getMaintenanceId();
            for (int i = 0; i < allMaintenance.size(); ++i) {
                    if (allMaintenance.get(i).isEmployed()) {
                        allMaintenance.remove(i);
                        i=-1; // reset counter otherwise loop will skip maintenance when size shrinks
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
        if(availableMaintenanceBox.getSelectionModel().isEmpty()){
            errorText.setText("Изберете поддръжка!");
        }else {
            if (currentMaintenanceList.getItems().size() < 1) {
                currentMaintenanceList.refresh();
                Maintenance maintenance = (Maintenance) availableMaintenanceBox.getSelectionModel().getSelectedItem();
                availableMaintenanceBox.getItems().remove(maintenance);
                currentMaintenanceList.getItems().add(maintenance);
                warehouseService.setEmploymentStatusMaintenance(maintenance, true);
                warehouseService.setWarehouseMaintenance(warehouse, maintenance);
            } else {
                errorText.setText("Може да има само един човек от поддръжка в склад!");
            }
        }
    }
    @FXML
    private void removeMaintenance() {
        currentMaintenanceList.getSelectionModel().selectFirst();
        if(currentMaintenanceList.getSelectionModel().getSelectedItem() != null){
            Maintenance maintenance = (Maintenance) currentMaintenanceList.getSelectionModel().getSelectedItem();
            currentMaintenanceList.getItems().remove(maintenance);
            availableMaintenanceBox.getItems().add(maintenance);
            warehouseService.setEmploymentStatusMaintenance(maintenance, false);
            warehouseService.setWarehouseMaintenance(warehouse, null);
        }
        else{
            errorText.setText("Няма хора за премахване!");
        }
    }

}
