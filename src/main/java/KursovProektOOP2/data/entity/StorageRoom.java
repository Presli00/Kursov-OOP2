package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Storage_room", schema = "warehouse")
public class StorageRoom implements Serializable {
    @Id
    @Column(name = "Storage_room_id", nullable = false)
    private int storageRoomId;
    @Column(name = "Size", nullable = false)
    private double size;
    @OneToOne
    @JoinColumn(name = "Climate_id", nullable = false)
    private Climate climateId;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductType productId;
    @Column(name = "Rented", nullable = false)
    private boolean Rented;
    @OneToMany(mappedBy = "storageRoom", fetch = FetchType.EAGER)
    private List<Formular> formulars; // list because it's an ordered collection and we know that the last element of type formular will always be the active one
    @ManyToOne
    @JoinColumn(name="WarehouseID")
    private Warehouse warehouse;

    public void addFormular(Formular formular) {
        formulars.add(formular);
        formular.setStorageRoom(this);
    }

    public boolean isRented() {
        return Rented;
    }

    public void setRented(boolean rented) {
        Rented = rented;
    }

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

    public ProductType getProductId() {
        return productId;
    }

    public void setProductId(ProductType productId) {
        this.productId = productId;
    }

    public List<Formular> getFormulars() {
        return formulars;
    }

    public void setFormulars(List<Formular> formulars) {
        this.formulars = formulars;
    }

    public Warehouse getwarehouse() {
        return warehouse;
    }

    public void setwarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageRoom that = (StorageRoom) o;

        if (storageRoomId != that.storageRoomId) return false;
        if (Double.compare(that.size, size) != 0) return false;
        if (climateId != that.climateId) return false;
        if (formulars != that.formulars) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "StorageRoom{" +
                "storageRoomId=" + storageRoomId +
                ", size=" + size +
                ", climateId=" + climateId +
                ", productId=" + productId +
                ", historyId=" + formulars +
                '}';
    }
}
