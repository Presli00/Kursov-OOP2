package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Table(name = "Owner", schema = "warehouse")
@Entity
public class Owner implements Serializable {
    @Id
    @Column(name = "idOwner", nullable = false)
    private int idOwner;
    @OneToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User userId;
    @Column(name = "warehousesAmount", nullable = false)
    private int warehousesAmount;
    @OneToMany
    @JoinColumn(name = "Warehouses")
    private Set<Warehouse> warehouses;

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public int getWarehousesAmount() {
        return warehousesAmount;
    }

    public void setWarehousesAmount(int warehousesAmount) {
        this.warehousesAmount = warehousesAmount;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return idOwner == owner.idOwner && warehousesAmount == owner.warehousesAmount && userId.equals(owner.userId);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "idOwner=" + idOwner +
                ", userId=" + userId +
                ", warehousesAmount=" + warehousesAmount +
                ", warehouses="+warehouses+
                '}';
    }
}
