package KursovProektOOP2.data.entity;

import javax.persistence.*;

@Table(name = "Owner", schema = "warehouse")
@Entity
public class Owner {
    @Id
    @Column(name = "idOwner", nullable = false)
    private int idOwner;
    @Column(name = "warehousesAmount", nullable = false)
    private int warehousesAmount;
    @OneToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User userId;

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public int getWarehousesAmount() {
        return warehousesAmount;
    }

    public void setWarehousesAmount(int warehousesAmount) {
        this.warehousesAmount = warehousesAmount;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
                ", warehousesAmount=" + warehousesAmount +
                ", userId=" + userId +
                '}';
    }
}
