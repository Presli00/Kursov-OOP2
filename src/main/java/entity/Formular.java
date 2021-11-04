package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Table(name = "Formular", schema = "warehouse")
@Entity
public class Formular implements Serializable {
    @Id
    @Column(name = "Formular_id", nullable = false)
    private int formularId;
    @OneToOne
    @JoinColumn(name = "Renter_id", nullable = false)
    private RenterInformation renterId;
    @Column(name = "Period_begin", nullable = false)
    private Timestamp periodBegin;
    @Column(name = "Period_end", nullable = false)
    private Timestamp periodEnd;
    @Column(name = "Price", nullable = false)
    private double price;

    public int getFormularId() {
        return formularId;
    }

    public void setFormularId(int formularId) {
        this.formularId = formularId;
    }

    public RenterInformation getRenterId() {
        return renterId;
    }

    public void setRenterId(RenterInformation renterId) {
        this.renterId = renterId;
    }

    public Timestamp getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(Timestamp periodBegin) {
        this.periodBegin = periodBegin;
    }


    public Timestamp getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Timestamp periodEnd) {
        this.periodEnd = periodEnd;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Formular formular = (Formular) o;

        if (formularId != formular.formularId) return false;
        if (renterId != formular.renterId) return false;
        if (Double.compare(formular.price, price) != 0) return false;
        if (periodBegin != null ? !periodBegin.equals(formular.periodBegin) : formular.periodBegin != null)
            return false;
        if (periodEnd != null ? !periodEnd.equals(formular.periodEnd) : formular.periodEnd != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Formular{" +
                "formularId=" + formularId +
                ", renterId=" + renterId +
                ", periodBegin=" + periodBegin +
                ", periodEnd=" + periodEnd +
                ", price=" + price +
                '}';
    }
}
