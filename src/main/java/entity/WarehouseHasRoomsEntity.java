package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "warehouse_has_rooms", schema = "warehouse", catalog = "")
public class WarehouseHasRoomsEntity {
    private int warehouseHasRoomsId;
    private int warehouseId;
    private int roomId;
    private Collection<WarehouseEntity> warehousesByWarehouseHasRoomsId;
    private WarehouseEntity warehouseByWarehouseId;
    private StorageRoomEntity storageRoomByRoomId;

    @Id
    @Column(name = "WarehouseHasRooms_id")
    public int getWarehouseHasRoomsId() {
        return warehouseHasRoomsId;
    }

    public void setWarehouseHasRoomsId(int warehouseHasRoomsId) {
        this.warehouseHasRoomsId = warehouseHasRoomsId;
    }

    @Basic
    @Column(name = "Warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Basic
    @Column(name = "Room_id")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseHasRoomsEntity that = (WarehouseHasRoomsEntity) o;

        if (warehouseHasRoomsId != that.warehouseHasRoomsId) return false;
        if (warehouseId != that.warehouseId) return false;
        if (roomId != that.roomId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = warehouseHasRoomsId;
        result = 31 * result + warehouseId;
        result = 31 * result + roomId;
        return result;
    }

    @OneToMany(mappedBy = "warehouseHasRoomsByRoomsId")
    public Collection<WarehouseEntity> getWarehousesByWarehouseHasRoomsId() {
        return warehousesByWarehouseHasRoomsId;
    }

    public void setWarehousesByWarehouseHasRoomsId(Collection<WarehouseEntity> warehousesByWarehouseHasRoomsId) {
        this.warehousesByWarehouseHasRoomsId = warehousesByWarehouseHasRoomsId;
    }

    @ManyToOne
    @JoinColumn(name = "Warehouse_id", referencedColumnName = "Warehouse_id", nullable = false)
    public WarehouseEntity getWarehouseByWarehouseId() {
        return warehouseByWarehouseId;
    }

    public void setWarehouseByWarehouseId(WarehouseEntity warehouseByWarehouseId) {
        this.warehouseByWarehouseId = warehouseByWarehouseId;
    }

    @ManyToOne
    @JoinColumn(name = "Room_id", referencedColumnName = "Storage_room_id", nullable = false)
    public StorageRoomEntity getStorageRoomByRoomId() {
        return storageRoomByRoomId;
    }

    public void setStorageRoomByRoomId(StorageRoomEntity storageRoomByRoomId) {
        this.storageRoomByRoomId = storageRoomByRoomId;
    }
}
