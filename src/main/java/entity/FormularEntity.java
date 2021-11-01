package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "formular", schema = "warehouse", catalog = "")
public class FormularEntity {
    private int formularId;
    private int renterId;
    private Timestamp periodBegin;
    private Timestamp periodEnd;
    private double price;
    private Collection<RentalHistoryEntity> rentalHistoriesByFormularId;

    @Id
    @Column(name = "Formular_id")
    public int getFormularId() {
        return formularId;
    }

    public void setFormularId(int formularId) {
        this.formularId = formularId;
    }

    @Basic
    @Column(name = "Renter_id")
    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    @Basic
    @Column(name = "Period_begin")
    public Timestamp getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(Timestamp periodBegin) {
        this.periodBegin = periodBegin;
    }

    @Basic
    @Column(name = "Period_end")
    public Timestamp getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Timestamp periodEnd) {
        this.periodEnd = periodEnd;
    }

    @Basic
    @Column(name = "Price")
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

        FormularEntity that = (FormularEntity) o;

        if (formularId != that.formularId) return false;
        if (renterId != that.renterId) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (periodBegin != null ? !periodBegin.equals(that.periodBegin) : that.periodBegin != null) return false;
        if (periodEnd != null ? !periodEnd.equals(that.periodEnd) : that.periodEnd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = formularId;
        result = 31 * result + renterId;
        result = 31 * result + (periodBegin != null ? periodBegin.hashCode() : 0);
        result = 31 * result + (periodEnd != null ? periodEnd.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "formularByFormularId")
    public Collection<RentalHistoryEntity> getRentalHistoriesByFormularId() {
        return rentalHistoriesByFormularId;
    }

    public void setRentalHistoriesByFormularId(Collection<RentalHistoryEntity> rentalHistoriesByFormularId) {
        this.rentalHistoriesByFormularId = rentalHistoriesByFormularId;
    }
}
