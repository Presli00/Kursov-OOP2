package KursovProektOOP2.data.services;

import KursovProektOOP2.controllers.Main;
import KursovProektOOP2.data.entity.*;
import KursovProektOOP2.data.repository.MaintenanceRepository;
import KursovProektOOP2.data.repository.StorageRoomRepository;
import KursovProektOOP2.data.repository.WarehouseRepository;

import java.util.List;

public class WarehouseService {
    public final MaintenanceRepository maintenanceRepository = MaintenanceRepository.getInstance();
    public final WarehouseRepository repository = WarehouseRepository.getInstance();
    public final StorageRoomRepository storageRoomRepository = StorageRoomRepository.getInstance();

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

    public void setRoomRented(StorageRoom room, boolean bool){
        room.setRented(bool);
        storageRoomRepository.update(room);
    }

    public List<Warehouse> getAllWarehouses(){
        return repository.getAll();
    }

    public StorageRoom getStorageRoomByID(int id){
        return (StorageRoom) storageRoomRepository.getById(id).get();
    }

    public Warehouse getWarehouseByID(int id){
        return (Warehouse) repository.getById(id).get();
    }

    public Maintenance getMaintenanceByID(int id){
        return (Maintenance) maintenanceRepository.getById(id).get();
    }

    public List<Maintenance> getAllMaintenance(){
        return maintenanceRepository.getAll();
    }

    public void updateWarehouse(Warehouse warehouse){
        repository.update(warehouse);
    }

    public void setWarehouseMaintenance(Warehouse warehouse, Maintenance maintenance){
        warehouse.setMaintenanceId(maintenance);
        repository.update(warehouse);
    }

    public void setEmploymentStatusMaintenance(Maintenance maintenance, boolean bool){
        maintenance.setEmployed(bool);
        maintenanceRepository.update(maintenance);
    }

    public void createNewRoom(double size, Climate climate, ProductType productType, Warehouse warehouse){
        StorageRoom room = new StorageRoom();
        room.setStorageRoomId(0);
        room.setSize(size);
        room.setClimateId(climate);
        room.setProductId(productType);
        room.setRented(false);
        room.setwarehouse(warehouse);
        storageRoomRepository.save(room);
    }

    public void deleteRoom(StorageRoom room){
        storageRoomRepository.delete(room);
    }

    public void deleteWarehouse(Warehouse warehouse){
        repository.delete(warehouse);
    }
}
