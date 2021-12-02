package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(name = "Warehouse", schema = "warehouse")
@Entity
public class Warehouse implements Serializable {
    @Id
    @Column(name = "Warehouse_id", nullable = false)
    private int warehouseId;
    @Column(name = "Warehouse_name", nullable = false)
    private String warehouseName;
    @ManyToOne
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
    @JoinColumn(name = "Agents_id")
    private AgentsList agentsId;

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

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
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

    public Maintenance getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Maintenance maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public AgentsList getAgentSId() {
        return agentsId;
    }

    public void setAgentSId(AgentsList agentSId) {
        this.agentsId = agentSId;
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
        if (agentsId != warehouse.agentsId) return false;
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
                ", maintenanceId=" + maintenanceId +
                ", agentsId=" + agentsId +
                '}';
    }
}
