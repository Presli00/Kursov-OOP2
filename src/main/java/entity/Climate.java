package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Climate {
    private int climateId;
    private String climate;
    private Collection<StorageRoom> storageRoomsByClimateId;

    @Id
    @Column(name = "Climate_id")
    public int getClimateId() {
        return climateId;
    }

    public void setClimateId(int climateId) {
        this.climateId = climateId;
    }

    @Basic
    @Column(name = "Climate")
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
    public int hashCode() {
        int result = climateId;
        result = 31 * result + (climate != null ? climate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "climateByClimateId")
    public Collection<StorageRoom> getStorageRoomsByClimateId() {
        return storageRoomsByClimateId;
    }

    public void setStorageRoomsByClimateId(Collection<StorageRoom> storageRoomsByClimateId) {
        this.storageRoomsByClimateId = storageRoomsByClimateId;
    }
}
