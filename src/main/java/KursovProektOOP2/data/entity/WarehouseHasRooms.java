package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Warehouse_has_rooms", schema = "warehouse")
public class WarehouseHasRooms implements Serializable {
    @Id
    @Column(name = "WarehouseHasRooms_id", nullable = false)
    private int warehouseHasRoomsId;
    @OneToMany
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Set<StorageRoom> warehouseId;
    @OneToMany
    @JoinColumn(name = "Room_id", nullable = false)
    private Set<StorageRoom> roomId;

    public int getWarehouseHasRoomsId() {
        return warehouseHasRoomsId;
    }

    public void setWarehouseHasRoomsId(int warehouseHasRoomsId) {
        this.warehouseHasRoomsId = warehouseHasRoomsId;
    }

    public Set<StorageRoom> getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Set<StorageRoom> warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Set<StorageRoom> getRoomId() {
        return roomId;
    }

    public void setRoomId(Set<StorageRoom> roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseHasRooms that = (WarehouseHasRooms) o;

        if (warehouseHasRoomsId != that.warehouseHasRoomsId) return false;
        if (roomId != that.roomId) return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "WarehouseHasRooms{" +
                "warehouseHasRoomsId=" + warehouseHasRoomsId +
                ", warehouseId=" + warehouseId +
                ", roomId=" + roomId +
                '}';
    }
}
