package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "warehouse", schema = "warehouse", catalog = "")
public class WarehouseEntity {
    private int warehouseId;
    private String warehouseName;
    private int cityId;
    private String street;
    private int numberOfStorageRooms;
    private int roomsId;
    private int maintanenceId;
    private int agentSId;
    private Collection<AgentsListEntity> agentsListsByWarehouseId;
    private CityEntity cityByCityId;
    private WarehouseHasRoomsEntity warehouseHasRoomsByRoomsId;
    private AgentsListEntity agentsListByAgentSId;
    private Collection<WarehouseHasRoomsEntity> warehouseHasRoomsByWarehouseId;

    @Id
    @Column(name = "Warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Basic
    @Column(name = "warehouse_name")
    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Basic
    @Column(name = "city_id")
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
    @Column(name = "rooms_id")
    public int getRoomsId() {
        return roomsId;
    }

    public void setRoomsId(int roomsId) {
        this.roomsId = roomsId;
    }

    @Basic
    @Column(name = "maintanence_id")
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

        WarehouseEntity that = (WarehouseEntity) o;

        if (warehouseId != that.warehouseId) return false;
        if (cityId != that.cityId) return false;
        if (numberOfStorageRooms != that.numberOfStorageRooms) return false;
        if (roomsId != that.roomsId) return false;
        if (maintanenceId != that.maintanenceId) return false;
        if (agentSId != that.agentSId) return false;
        if (warehouseName != null ? !warehouseName.equals(that.warehouseName) : that.warehouseName != null)
            return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;

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
    public Collection<AgentsListEntity> getAgentsListsByWarehouseId() {
        return agentsListsByWarehouseId;
    }

    public void setAgentsListsByWarehouseId(Collection<AgentsListEntity> agentsListsByWarehouseId) {
        this.agentsListsByWarehouseId = agentsListsByWarehouseId;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "City_id", nullable = false)
    public CityEntity getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(CityEntity cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @ManyToOne
    @JoinColumn(name = "rooms_id", referencedColumnName = "WarehouseHasRooms_id", nullable = false)
    public WarehouseHasRoomsEntity getWarehouseHasRoomsByRoomsId() {
        return warehouseHasRoomsByRoomsId;
    }

    public void setWarehouseHasRoomsByRoomsId(WarehouseHasRoomsEntity warehouseHasRoomsByRoomsId) {
        this.warehouseHasRoomsByRoomsId = warehouseHasRoomsByRoomsId;
    }

    @ManyToOne
    @JoinColumn(name = "Agent/s_id", referencedColumnName = "AgentsList_id", nullable = false)
    public AgentsListEntity getAgentsListByAgentSId() {
        return agentsListByAgentSId;
    }

    public void setAgentsListByAgentSId(AgentsListEntity agentsListByAgentSId) {
        this.agentsListByAgentSId = agentsListByAgentSId;
    }

    @OneToMany(mappedBy = "warehouseByWarehouseId")
    public Collection<WarehouseHasRoomsEntity> getWarehouseHasRoomsByWarehouseId() {
        return warehouseHasRoomsByWarehouseId;
    }

    public void setWarehouseHasRoomsByWarehouseId(Collection<WarehouseHasRoomsEntity> warehouseHasRoomsByWarehouseId) {
        this.warehouseHasRoomsByWarehouseId = warehouseHasRoomsByWarehouseId;
    }
}
