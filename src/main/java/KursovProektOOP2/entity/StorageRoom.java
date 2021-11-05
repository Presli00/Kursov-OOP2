package KursovProektOOP2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "storage_room", schema = "warehouse")
@Entity
public class StorageRoom implements Serializable {
    @Id
    @Column(name = "Storage_room_id", nullable = false)
    private int storageRoomId;
    @Column(name = "Size", nullable = false)
    private double size;
    @OneToOne
    @JoinColumn(name = "Climate_id", nullable = false)
    private Climate climateId;
    @OneToMany
    @JoinColumn(name = "Product_id", nullable = false)
    private Set<ProductType> productId;
    @OneToOne
    @JoinColumn(name = "History_id", nullable = false)
    private RentalHistory historyId;

    public int getStorageRoomId() {
        return storageRoomId;
    }

    public void setStorageRoomId(int storageRoomId) {
        this.storageRoomId = storageRoomId;
    }


    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Climate getClimateId() {
        return climateId;
    }

    public void setClimateId(Climate climateId) {
        this.climateId = climateId;
    }

    public Set<ProductType> getProductId() {
        return productId;
    }

    public void setProductId(Set<ProductType> productId) {
        this.productId = productId;
    }

    public RentalHistory getHistoryId() {
        return historyId;
    }

    public void setHistoryId(RentalHistory historyId) {
        this.historyId = historyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageRoom that = (StorageRoom) o;

        if (storageRoomId != that.storageRoomId) return false;
        if (Double.compare(that.size, size) != 0) return false;
        if (climateId != that.climateId) return false;
        if (productId != that.productId) return false;
        if (historyId != that.historyId) return false;

        return true;
    }

    @Override
    public String toString() {
        return "StorageRoom{" +
                "storageRoomId=" + storageRoomId +
                ", size=" + size +
                ", climateId=" + climateId +
                ", productId=" + productId +
                ", historyId=" + historyId +
                '}';
    }
}
