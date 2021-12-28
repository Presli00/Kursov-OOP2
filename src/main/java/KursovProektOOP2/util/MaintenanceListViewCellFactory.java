package KursovProektOOP2.util;

import KursovProektOOP2.data.entity.Maintenance;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MaintenanceListViewCellFactory implements Callback<ListView<Maintenance>,ListCell<Maintenance>> {
    @Override
    public ListCell<Maintenance> call(ListView<Maintenance> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Maintenance maintenance, boolean empty) {
                super.updateItem(maintenance, empty);
                if (empty || maintenance == null) {
                    setText(null);
                } else {
                    setText(maintenance.getName());
                }
            }
        };
    }
}
