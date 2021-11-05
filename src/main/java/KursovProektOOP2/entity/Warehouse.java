package KursovProektOOP2.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Warehouse",schema = "warehouse")
@Entity
public class Warehouse implements Serializable {
    @Id
    @Column(name = "Warehouse_id", nullable = false)
    private int warehouseId;
    @Column(name = "Warehouse_name", nullable = false)
    private String warehouseName;
    @OneToOne
    @JoinColumn(name = "City_id", nullable = false)
    private City cityId;
    @Column(name = "Street", nullable = false)
    private String street;
    @Column(name = "Number_of_storage_rooms", nullable = false)
    private int numberOfStorageRooms;
    @ManyToOne
    @JoinColumn(name = "Rooms_id", nullable = false)
    private WarehouseHasRooms roomsId;
    @OneToOne
    @JoinColumn(name = "Maintanence_id", nullable = false)
    private Maintenance maintanenceId;
    @OneToOne
    @JoinColumn(name = "Agent/s_id", nullable = false)
    private AgentsList agentSId;


    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }


    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumberOfStorageRooms() {
        return numberOfStorageRooms;
    }

    public void setNumberOfStorageRooms(int numberOfStorageRooms) {
        this.numberOfStorageRooms = numberOfStorageRooms;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public WarehouseHasRooms getRoomsId() {
        return roomsId;
    }

    public void setRoomsId(WarehouseHasRooms roomsId) {
        this.roomsId = roomsId;
    }

    public Maintenance getMaintanenceId() {
        return maintanenceId;
    }

    public void setMaintanenceId(Maintenance maintanenceId) {
        this.maintanenceId = maintanenceId;
    }

    public AgentsList getAgentSId() {
        return agentSId;
    }

    public void setAgentSId(AgentsList agentSId) {
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
    public String toString() {
        return "Warehouse{" +
                "warehouseId=" + warehouseId +
                ", warehouseName='" + warehouseName + '\'' +
                ", cityId=" + cityId +
                ", street='" + street + '\'' +
                ", numberOfStorageRooms=" + numberOfStorageRooms +
                ", roomsId=" + roomsId +
                ", maintanenceId=" + maintanenceId +
                ", agentSId=" + agentSId +
                '}';
    }
}
