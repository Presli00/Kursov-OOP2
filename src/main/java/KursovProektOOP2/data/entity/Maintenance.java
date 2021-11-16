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
    @ManyToOne
    @JoinColumn(name = "City_id", nullable = false)
    private City cityId;

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

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maintenance that = (Maintenance) o;

        if (maintenanceId != that.maintenanceId) return false;
        if (cityId != that.cityId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenanceId=" + maintenanceId +
                ", name='" + name + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
