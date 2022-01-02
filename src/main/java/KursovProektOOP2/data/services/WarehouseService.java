package KursovProektOOP2.data.services;

import KursovProektOOP2.data.entity.City;
import KursovProektOOP2.data.entity.Maintenance;
import KursovProektOOP2.data.entity.Owner;
import KursovProektOOP2.data.entity.Warehouse;
import KursovProektOOP2.data.repository.AgentRepository;
import KursovProektOOP2.data.repository.MaintenanceRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;

public class WarehouseService {
    public final MaintenanceRepository maintenanceRepository = MaintenanceRepository.getInstance();
    public final WarehouseRepository repository = WarehouseRepository.getInstance();

    public static WarehouseService getInstance(){
        return WarehouseService.WarehouseServiceHolder.INSTANCE;
    }

    private static class WarehouseServiceHolder{
        public static final WarehouseService INSTANCE = new WarehouseService();
    }

    public void addWarehouse(String warehouseName, City city, String street, int rooms, int id, boolean empty, Owner owner){
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(0);
        warehouse.setWarehouseName(warehouseName);
        warehouse.setCityId(city);
        warehouse.setStreet(street);
        warehouse.setNumberOfStorageRooms(rooms);
        if (empty) {
            warehouse.setMaintenanceId(null);
        }else{
            Maintenance maintenance = (Maintenance) maintenanceRepository.getById(id).get();
            warehouse.setMaintenanceId(maintenance);
            maintenance.setEmployed(true);
            maintenanceRepository.update(maintenance);
        }
        warehouse.setOwnerId(owner);
        warehouse.setAgentsId(null);
        repository.save(warehouse);
    }

}
