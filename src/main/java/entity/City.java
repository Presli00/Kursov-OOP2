package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class City {
    private int cityId;
    private String city;
    private Collection<RenterInformation> renterInformationsByCityId;
    private Collection<Warehouse> warehousesByCityId;

    @Id
    @Column(name = "City_id")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city1 = (City) o;

        if (cityId != city1.cityId) return false;
        if (city != null ? !city.equals(city1.city) : city1.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<RenterInformation> getRenterInformationsByCityId() {
        return renterInformationsByCityId;
    }

    public void setRenterInformationsByCityId(Collection<RenterInformation> renterInformationsByCityId) {
        this.renterInformationsByCityId = renterInformationsByCityId;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<Warehouse> getWarehousesByCityId() {
        return warehousesByCityId;
    }

    public void setWarehousesByCityId(Collection<Warehouse> warehousesByCityId) {
        this.warehousesByCityId = warehousesByCityId;
    }
}
