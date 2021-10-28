package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Maintenance {
    private int maintenanceId;
    private String name;
    private String city;
    private Collection<Warehouse> warehousesByMaintenanceId;

    @Id
    @Column(name = "Maintenance_id")
    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maintenance that = (Maintenance) o;

        if (maintenanceId != that.maintenanceId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = maintenanceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "maintenanceByMaintanenceId")
    public Collection<Warehouse> getWarehousesByMaintenanceId() {
        return warehousesByMaintenanceId;
    }

    public void setWarehousesByMaintenanceId(Collection<Warehouse> warehousesByMaintenanceId) {
        this.warehousesByMaintenanceId = warehousesByMaintenanceId;
    }
}
