package KursovProektOOP2.controllers.Spravki;

import KursovProektOOP2.data.entity.Formular;
import KursovProektOOP2.data.entity.Maintenance;
import KursovProektOOP2.data.repository.MaintenanceRepository;
import KursovProektOOP2.models.MaintenanceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class maintenanceSpravka {
@FXML
TableView<MaintenanceModel> table;
    @FXML
    TableColumn number;
    @FXML
    TableColumn maintenanceName;
    @FXML
    TableColumn works;
    @FXML
    TableColumn city;
    @FXML
    TableColumn employeed;

    public final MaintenanceRepository maintenanceRepository=MaintenanceRepository.getInstance();
@FXML
private void initialize(){
    List<Maintenance> maintenanceEntities = new ArrayList<>(maintenanceRepository.getAll());
    ObservableList<MaintenanceModel> maintenanceModels = FXCollections.observableArrayList();
    for (int i = 0; i < maintenanceEntities.size(); i++) {
        maintenanceModels.add(new MaintenanceModel(i + 1,maintenanceEntities.get(i).getName(),
                maintenanceEntities.get(i).getWarehouse() == null ? "" : maintenanceEntities.get(i).getWarehouse().getWarehouseName(),
                maintenanceEntities.get(i).getWarehouse()== null ? "" : maintenanceEntities.get(i).getWarehouse().getCityId().getCity(),
                maintenanceEntities.get(i).isEmployed() ? "Да": "Не"));

    }
    number.setCellValueFactory(new PropertyValueFactory<>("id"));
    maintenanceName.setCellValueFactory(new PropertyValueFactory<>("maintenanceName"));
    works.setCellValueFactory(new PropertyValueFactory<>("worksIn"));
    city.setCellValueFactory(new PropertyValueFactory<>("City"));
    employeed.setCellValueFactory(new PropertyValueFactory<>("isEmployeed"));
    for (int i = 0; i < maintenanceModels.size(); i++) {
        table.getItems().add(maintenanceModels.get(i));
    }

}
}
