package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "climate", schema = "warehouse", catalog = "")
public class ClimateEntity {
    private int climateId;
    private String climate;
    private Collection<StorageRoomEntity> storageRoomsByClimateId;

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

        ClimateEntity that = (ClimateEntity) o;

        if (climateId != that.climateId) return false;
        if (climate != null ? !climate.equals(that.climate) : that.climate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = climateId;
        result = 31 * result + (climate != null ? climate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "climateByClimateId")
    public Collection<StorageRoomEntity> getStorageRoomsByClimateId() {
        return storageRoomsByClimateId;
    }

    public void setStorageRoomsByClimateId(Collection<StorageRoomEntity> storageRoomsByClimateId) {
        this.storageRoomsByClimateId = storageRoomsByClimateId;
    }
}
