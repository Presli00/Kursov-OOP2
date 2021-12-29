package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(name = "Maintenance", schema = "warehouse")
@Entity
public class Maintenance implements Serializable {
    @Id
    @Column(name = "Maintenance_id", nullable = false)
    private int maintenanceId;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "isEmployed", nullable = false)
    private boolean isEmployed;
    @OneToOne(mappedBy = "maintenanceId")
    private Warehouse warehouse;

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maintenance that = (Maintenance) o;

        if (maintenanceId != that.maintenanceId) return false;
        if (isEmployed != that.isEmployed) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenanceId=" + maintenanceId +
                ", name='" + name + '\'' +
                ", cityId=" + isEmployed +
                '}';
    }
}
