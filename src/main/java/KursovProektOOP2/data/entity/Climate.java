package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Climate", schema = "warehouse")
@Entity
public class Climate implements Serializable {
    @Id
    @Column(name = "Climate_id", nullable = false)
    private int climateId;
    @Column(name = "Climate", nullable = false)
    private String climate;


    public int getClimateId() {
        return climateId;
    }

    public void setClimateId(int climateId) {
        this.climateId = climateId;
    }


    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Climate climate1 = (Climate) o;

        if (climateId != climate1.climateId) return false;
        if (climate != null ? !climate.equals(climate1.climate) : climate1.climate != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Climate{" +
                "climateId=" + climateId +
                ", climate='" + climate + '\'' +
                '}';
    }
}
