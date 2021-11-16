package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Renter_information", schema = "warehouse")
public class RenterInformation implements Serializable {
    @Id
    @Column(name = "Renter_id", nullable = false)
    private int renterId;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Phone", nullable = false)
    private String phone;
    @ManyToOne
    @JoinColumn(name = "City_id", nullable = false)
    private City cityId;
    @Column(name = "Street", nullable = false)
    private String street;

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RenterInformation that = (RenterInformation) o;

        if (renterId != that.renterId) return false;
        if (cityId != that.cityId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "RenterInformation{" +
                "renterId=" + renterId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", cityId=" + cityId +
                ", street='" + street + '\'' +
                '}';
    }
}
