package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Table(name = "warehouse_has_rooms", schema = "warehouse")
@Entity
public class WarehouseHasRooms implements Serializable {
    @Id
    @Column(name = "WarehouseHasRooms_id", nullable = false)
    private int warehouseHasRoomsId;
    @OneToOne
    @JoinColumn(name = "Warehouse_id", nullable = false)
    private Warehouse warehouseId;
    @OneToOne
    @JoinColumn(name = "Room_id", nullable = false)
    private StorageRoom roomId;

    public int getWarehouseHasRoomsId() {
        return warehouseHasRoomsId;
    }

    public void setWarehouseHasRoomsId(int warehouseHasRoomsId) {
        this.warehouseHasRoomsId = warehouseHasRoomsId;
    }

    public Warehouse getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Warehouse warehouseId) {
        this.warehouseId = warehouseId;
    }

    public StorageRoom getRoomId() {
        return roomId;
    }

    public void setRoomId(StorageRoom roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseHasRooms that = (WarehouseHasRooms) o;

        if (warehouseHasRoomsId != that.warehouseHasRoomsId) return false;
        if (warehouseId != that.warehouseId) return false;
        if (roomId != that.roomId) return false;

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
