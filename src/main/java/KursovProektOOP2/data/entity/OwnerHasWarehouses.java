package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Owner_has_warehouses", schema = "warehouse")
public class OwnerHasWarehouses implements Serializable {
    @Id
    @Column(name = "warehouses_id", nullable = false)
    private int warehousesId;
    @ManyToOne
    @JoinColumn(name = "idFromOwner", nullable = false)
    private Owner idFromOwner;
    @OneToMany
    @JoinColumn(name = "warehouseWithRoomsID", nullable = false)
    private Set<WarehouseHasRooms> warehouseWithRoomsId;

    public int getWarehousesId() {
        return warehousesId;
    }

    public void setWarehousesId(int warehousesId) {
        this.warehousesId = warehousesId;
    }

    public Owner getIdFromOwner() {
        return idFromOwner;
    }

    public void setIdFromOwner(Owner idFromOwner) {
        this.idFromOwner = idFromOwner;
    }

    public Set<WarehouseHasRooms> getWarehouseWithRoomsId() {
        return warehouseWithRoomsId;
    }

    public void setWarehouseWithRoomsId(Set<WarehouseHasRooms> warehouseWithRoomsId) {
        this.warehouseWithRoomsId = warehouseWithRoomsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerHasWarehouses that = (OwnerHasWarehouses) o;

        if (warehousesId != that.warehousesId) return false;
        if (idFromOwner != null ? !idFromOwner.equals(that.idFromOwner) : that.idFromOwner != null) return false;
        if (warehouseWithRoomsId != null ? !warehouseWithRoomsId.equals(that.warehouseWithRoomsId) : that.warehouseWithRoomsId != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "OwnerHasWarehouses{" +
                "warehousesId=" + warehousesId +
                ", idFromOwner=" + idFromOwner +
                ", warehouseWithRoomsId=" + warehouseWithRoomsId +
                '}';
    }
}
