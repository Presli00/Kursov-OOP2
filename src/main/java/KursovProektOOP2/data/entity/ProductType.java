package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Product_type", schema = "warehouse")
public class ProductType implements Serializable {
    @Id
    @Column(name = "Product_id", nullable = false)
    private int productId;
    @Column(name = "Type", nullable = false)
    private String type;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

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
    public String toString() {
        return "ProductType{" +
                "productId=" + productId +
                ", type='" + type + '\'' +
                '}';
    }
}
