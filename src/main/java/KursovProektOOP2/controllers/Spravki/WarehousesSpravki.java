package KursovProektOOP2.controllers.Spravki;

import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.OwnerRepository;
import KursovProektOOP2.models.OwnerModel;
import KursovProektOOP2.util.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class WarehousesSpravki {
    @FXML
    TableView<OwnerModel> table;
    @FXML
    TableColumn number;
    @FXML
    TableColumn owner;
    @FXML
    TableColumn firstName;
    @FXML
    TableColumn lastName;
    @FXML
    TableColumn warehouseName;
    @FXML
    TableColumn cityName;
    @FXML
    TableColumn address;

    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();

    @FXML
    private void initialize(){
        List<Owner> ownerEntities = new ArrayList<>(ownerRepository.getAll());
        ObservableList<OwnerModel> ownerModels = FXCollections.observableArrayList();

        for (int i = 0; i < ownerEntities.size(); i++) {
            if (UserSession.getRoleID().getRoleName().equals("Owner")){
                if(ownerEntities.get(i).getIdOwner() == UserSession.getOwner().getIdOwner()){ // Skip warehouses of the current user
                    continue;
                }
            }
            List<Warehouse> warehouses = new ArrayList<>(ownerEntities.get(i).getWarehouses());
            if (warehouses.size() == 0){
                ownerModels.add(new OwnerModel(i + 1,ownerEntities.get(i).getUserId().getUsername(),
                        ownerEntities.get(i).getUserId().getFirstName(),
                        ownerEntities.get(i).getUserId().getLastName(),
                        "",
                        "",
                        ""));
            }else{
                for (int j = 0; j < warehouses.size(); j++) {
                    ownerModels.add(new OwnerModel(i + 1,ownerEntities.get(i).getUserId().getUsername(),
                            ownerEntities.get(i).getUserId().getFirstName(),
                            ownerEntities.get(i).getUserId().getLastName(),
                            warehouses.get(j).getWarehouseName(),
                            warehouses.get(j).getCityId().getCity(),
                            warehouses.get(j).getStreet()));
                }
            }

        }
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        owner.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        warehouseName.setCellValueFactory(new PropertyValueFactory<>("warehouseName"));
        cityName.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        for (int i = 0; i < ownerModels.size(); i++) {
            table.getItems().add(ownerModels.get(i));
        }

    }
}
