package KursovProektOOP2.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="Maintenance", schema = "warehouse")
@Entity
public class Maintenance implements Serializable {
    @Id
    @Column(name = "Maintenance_id", nullable = false)
    private int maintenanceId;
    @Column(name = "Name", nullable = false)
    private String name;
    @OneToOne
    @JoinColumn(name = "City", nullable = false)
    private City city;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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
    public String toString() {
        return "Maintenance{" +
                "maintenanceId=" + maintenanceId +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
