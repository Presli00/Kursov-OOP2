package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Warehouse {
    private int warehouseId;
    private String warehouseName;
    private int cityId;
    private String street;
    private int numberOfStorageRooms;
    private int roomsId;
    private int maintanenceId;
    private int agentSId;
    private Collection<AgentsList> agentsListsByWarehouseId;
    private City cityByCityId;
    private WarehouseHasRooms warehouseHasRoomsByRoomsId;
    private Maintenance maintenanceByMaintanenceId;
    private AgentsList agentsListByAgentSId;
    private Collection<WarehouseHasRooms> warehouseHasRoomsByWarehouseId;

    @Id
    @Column(name = "Warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Basic
    @Column(name = "Warehouse_name")
    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Basic
    @Column(name = "City_id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "Number_of_storage_rooms")
    public int getNumberOfStorageRooms() {
        return numberOfStorageRooms;
    }

    public void setNumberOfStorageRooms(int numberOfStorageRooms) {
        this.numberOfStorageRooms = numberOfStorageRooms;
    }

    @Basic
    @Column(name = "Rooms_id")
    public int getRoomsId() {
        return roomsId;
    }

    public void setRoomsId(int roomsId) {
        this.roomsId = roomsId;
    }

    @Basic
    @Column(name = "Maintanence_id")
    public int getMaintanenceId() {
        return maintanenceId;
    }

    public void setMaintanenceId(int maintanenceId) {
        this.maintanenceId = maintanenceId;
    }

    @Basic
    @Column(name = "Agent/s_id")
    public int getAgentSId() {
        return agentSId;
    }

    public void setAgentSId(int agentSId) {
        this.agentSId = agentSId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warehouse warehouse = (Warehouse) o;

        if (warehouseId != warehouse.warehouseId) return false;
        if (cityId != warehouse.cityId) return false;
        if (numberOfStorageRooms != warehouse.numberOfStorageRooms) return false;
        if (roomsId != warehouse.roomsId) return false;
        if (maintanenceId != warehouse.maintanenceId) return false;
        if (agentSId != warehouse.agentSId) return false;
        if (warehouseName != null ? !warehouseName.equals(warehouse.warehouseName) : warehouse.warehouseName != null)
            return false;
        if (street != null ? !street.equals(warehouse.street) : warehouse.street != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = warehouseId;
        result = 31 * result + (warehouseName != null ? warehouseName.hashCode() : 0);
        result = 31 * result + cityId;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + numberOfStorageRooms;
        result = 31 * result + roomsId;
        result = 31 * result + maintanenceId;
        result = 31 * result + agentSId;
        return result;
    }

    @OneToMany(mappedBy = "warehouseByWarehouseId")
    public Collection<AgentsList> getAgentsListsByWarehouseId() {
        return agentsListsByWarehouseId;
    }

    public void setAgentsListsByWarehouseId(Collection<AgentsList> agentsListsByWarehouseId) {
        this.agentsListsByWarehouseId = agentsListsByWarehouseId;
    }

    @ManyToOne
    @JoinColumn(name = "City_id", referencedColumnName = "City_id", nullable = false)
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @ManyToOne
    @JoinColumn(name = "Rooms_id", referencedColumnName = "WarehouseHasRooms_id", nullable = false)
    public WarehouseHasRooms getWarehouseHasRoomsByRoomsId() {
        return warehouseHasRoomsByRoomsId;
    }

    public void setWarehouseHasRoomsByRoomsId(WarehouseHasRooms warehouseHasRoomsByRoomsId) {
        this.warehouseHasRoomsByRoomsId = warehouseHasRoomsByRoomsId;
    }

    @ManyToOne
    @JoinColumn(name = "Maintanence_id", referencedColumnName = "Maintenance_id", nullable = false)
    public Maintenance getMaintenanceByMaintanenceId() {
        return maintenanceByMaintanenceId;
    }

    public void setMaintenanceByMaintanenceId(Maintenance maintenanceByMaintanenceId) {
        this.maintenanceByMaintanenceId = maintenanceByMaintanenceId;
    }

    @ManyToOne
    @JoinColumn(name = "Agent/s_id", referencedColumnName = "AgentsList_id", nullable = false)
    public AgentsList getAgentsListByAgentSId() {
        return agentsListByAgentSId;
    }

    public void setAgentsListByAgentSId(AgentsList agentsListByAgentSId) {
        this.agentsListByAgentSId = agentsListByAgentSId;
    }

    @OneToMany(mappedBy = "warehouseByWarehouseId")
    public Collection<WarehouseHasRooms> getWarehouseHasRoomsByWarehouseId() {
        return warehouseHasRoomsByWarehouseId;
    }

    public void setWarehouseHasRoomsByWarehouseId(Collection<WarehouseHasRooms> warehouseHasRoomsByWarehouseId) {
        this.warehouseHasRoomsByWarehouseId = warehouseHasRoomsByWarehouseId;
    }
}
