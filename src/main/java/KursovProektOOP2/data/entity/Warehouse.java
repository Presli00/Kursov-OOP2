package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Warehouse",schema = "warehouse")
@Entity
public class Warehouse implements Serializable {
    @Id
    @Column(name = "Warehouse_id", nullable = false)
    private int warehouseId;
    @OneToOne
    @JoinColumn(name = "City_id", nullable = false)
    private City cityId;
    @Column(name = "Street", nullable = false)
    private String street;
    @Column(name = "Number_of_storage_rooms", nullable = false)
    private int numberOfStorageRooms;
    @OneToOne
    @JoinColumn(name = "Maintenance_id", nullable = false)
    private Maintenance maintenanceId;
    @OneToOne
    @JoinColumn(name = "Agent/s_id", nullable = false)
    private AgentsList agentSId;


    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
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

    public Maintenance getMaintanenceId() {
        return maintenanceId;
    }

    public void setMaintanenceId(Maintenance maintanenceId) {
        this.maintenanceId = maintanenceId;
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
        if (maintenanceId != warehouse.maintenanceId) return false;
        if (agentSId != warehouse.agentSId) return false;
        if (street != null ? !street.equals(warehouse.street) : warehouse.street != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "warehouseId=" + warehouseId +
                ", cityId=" + cityId +
                ", street='" + street + '\'' +
                ", numberOfStorageRooms=" + numberOfStorageRooms +
                ", maintanenceId=" + maintenanceId +
                ", agentSId=" + agentSId +
                '}';
    }
}
