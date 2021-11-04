package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "product_type", schema = "warehouse", catalog = "")
public class ProductType {
    private int productId;
    private String type;
    private Collection<StorageRoom> storageRoomsByProductId;

    @Id
    @Column(name = "Product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductType that = (ProductType) o;

        if (productId != that.productId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productTypeByProductId")
    public Collection<StorageRoom> getStorageRoomsByProductId() {
        return storageRoomsByProductId;
    }

    public void setStorageRoomsByProductId(Collection<StorageRoom> storageRoomsByProductId) {
        this.storageRoomsByProductId = storageRoomsByProductId;
    }
}
