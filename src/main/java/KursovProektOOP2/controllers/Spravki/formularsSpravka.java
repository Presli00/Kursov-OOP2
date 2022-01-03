package KursovProektOOP2.controllers.Spravki;

import KursovProektOOP2.data.entity.Agent;
import KursovProektOOP2.data.entity.Formular;
import KursovProektOOP2.data.entity.StorageRoom;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.FormularRepository;
import KursovProektOOP2.data.repository.StorageRoomRepository;
import KursovProektOOP2.data.services.FormularService;
import KursovProektOOP2.data.services.WarehouseService;
import KursovProektOOP2.models.FormularModel;
import KursovProektOOP2.util.AgentListViewCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class formularsSpravka {

    @FXML
    TableView<FormularModel> table;
    @FXML
    TableColumn number;
    @FXML
    TableColumn warehouseName;
    @FXML
    TableColumn roomNumber;
    @FXML
    TableColumn startDate;
    @FXML
    TableColumn endDate;
    @FXML
    TableColumn price;
    public boolean fromWarehouseViewer;
    public int roomID;
    List<Formular> formularEntities;
    public final FormularService formularService = FormularService.getInstance();
    public final WarehouseService warehouseService = WarehouseService.getInstance();

    @FXML
    private void initialize(){
        new Thread(() -> {
            try {
                Thread.sleep(100); // Wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(fromWarehouseViewer){
                formularEntities = new ArrayList<>(warehouseService.getStorageRoomByID(roomID).getFormulars());
            }else{
                formularEntities = new ArrayList<>(formularService.getAllFormulars());
            }
            ObservableList<FormularModel> formularModels = FXCollections.observableArrayList();
            for (int i = 0; i < formularEntities.size(); i++) {
                formularModels.add(new FormularModel(i + 1,formularEntities.get(i).getStorageRoom().getwarehouse().getWarehouseName(),
                        formularEntities.get(i).getStorageRoom().getStorageRoomId(),
                        formularEntities.get(i).getPeriodBegin(),
                        formularEntities.get(i).getPeriodEnd(),
                        formularEntities.get(i).getPrice()));
            }
            number.setCellValueFactory(new PropertyValueFactory<>("id"));
            warehouseName.setCellValueFactory(new PropertyValueFactory<>("warehouseName"));
            roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
            startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            for (int i = 0; i < formularModels.size(); i++) {
                table.getItems().add(formularModels.get(i));
            }
            fromWarehouseViewer = false;

        }).start();
    }
}
