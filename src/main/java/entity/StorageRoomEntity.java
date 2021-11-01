package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "storage_room", schema = "warehouse", catalog = "")
public class StorageRoomEntity {
    private int storageRoomId;
    private double size;
    private int climateId;
    private int productId;
    private int historyId;
    private ClimateEntity climateByClimateId;
    private ProductTypeEntity productTypeByProductId;
    private RentalHistoryEntity rentalHistoryByHistoryId;
    private Collection<WarehouseHasRoomsEntity> warehouseHasRoomsByStorageRoomId;

    @Id
    @Column(name = "Storage_room_id")
    public int getStorageRoomId() {
        return storageRoomId;
    }

    public void setStorageRoomId(int storageRoomId) {
        this.storageRoomId = storageRoomId;
    }

    @Basic
    @Column(name = "Size")
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Basic
    @Column(name = "Climate_id")
    public int getClimateId() {
        return climateId;
    }

    public void setClimateId(int climateId) {
        this.climateId = climateId;
    }

    @Basic
    @Column(name = "Product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "History_id")
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageRoomEntity that = (StorageRoomEntity) o;

        if (storageRoomId != that.storageRoomId) return false;
        if (Double.compare(that.size, size) != 0) return false;
        if (climateId != that.climateId) return false;
        if (productId != that.productId) return false;
        if (historyId != that.historyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = storageRoomId;
        temp = Double.doubleToLongBits(size);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + climateId;
        result = 31 * result + productId;
        result = 31 * result + historyId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Climate_id", referencedColumnName = "Climate_id", nullable = false)
    public ClimateEntity getClimateByClimateId() {
        return climateByClimateId;
    }

    public void setClimateByClimateId(ClimateEntity climateByClimateId) {
        this.climateByClimateId = climateByClimateId;
    }

    @ManyToOne
    @JoinColumn(name = "Product_id", referencedColumnName = "Product_id", nullable = false)
    public ProductTypeEntity getProductTypeByProductId() {
        return productTypeByProductId;
    }

    public void setProductTypeByProductId(ProductTypeEntity productTypeByProductId) {
        this.productTypeByProductId = productTypeByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "History_id", referencedColumnName = "History_id", nullable = false)
    public RentalHistoryEntity getRentalHistoryByHistoryId() {
        return rentalHistoryByHistoryId;
    }

    public void setRentalHistoryByHistoryId(RentalHistoryEntity rentalHistoryByHistoryId) {
        this.rentalHistoryByHistoryId = rentalHistoryByHistoryId;
    }

    @OneToMany(mappedBy = "storageRoomByRoomId")
    public Collection<WarehouseHasRoomsEntity> getWarehouseHasRoomsByStorageRoomId() {
        return warehouseHasRoomsByStorageRoomId;
    }

    public void setWarehouseHasRoomsByStorageRoomId(Collection<WarehouseHasRoomsEntity> warehouseHasRoomsByStorageRoomId) {
        this.warehouseHasRoomsByStorageRoomId = warehouseHasRoomsByStorageRoomId;
    }
}
