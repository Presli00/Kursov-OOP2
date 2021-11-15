package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owner_has_warehouses", schema = "warehouse")
public class OwnerHasWarehouses {
    @Id
    @Column(name = "warehouses_id", nullable = false)
    private int warehousesId;
    @ManyToOne
    @JoinColumn(name = "idFromOwner", nullable = false)
    private Owner ownerId;
    @OneToMany
    @JoinColumn(name = "warehouseWithRoomsID", nullable = false)
    private Set<WarehouseHasRooms> warehouseHasRoomsId;

    public int getWarehousesId() {
        return warehousesId;
    }

    public void setWarehousesId(int warehousesId) {
        this.warehousesId = warehousesId;
    }

    public Owner getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Owner ownerId) {
        this.ownerId = ownerId;
    }

    public Set<WarehouseHasRooms> getWarehouseHasRoomsId() {
        return warehouseHasRoomsId;
    }

    public void setWarehouseHasRoomsId(Set<WarehouseHasRooms> warehouseHasRoomsId) {
        this.warehouseHasRoomsId = warehouseHasRoomsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerHasWarehouses that = (OwnerHasWarehouses) o;
        return warehousesId == that.warehousesId && ownerId.equals(that.ownerId) && warehouseHasRoomsId.equals(that.warehouseHasRoomsId);
    }

    @Override
    public String toString() {
        return "OwnerHasWarehouses{" +
                "warehousesId=" + warehousesId +
                ", ownerId=" + ownerId +
                ", warehouseHasRoomsId=" + warehouseHasRoomsId +
                '}';
    }
}
