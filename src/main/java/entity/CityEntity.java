package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "city", schema = "warehouse", catalog = "")
public class CityEntity {
    private int cityId;
    private String city;
    private Collection<RenterInformationEntity> renterInformationsByCityId;
    private Collection<WarehouseEntity> warehousesByCityId;

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

        CityEntity that = (CityEntity) o;

        if (cityId != that.cityId) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<RenterInformationEntity> getRenterInformationsByCityId() {
        return renterInformationsByCityId;
    }

    public void setRenterInformationsByCityId(Collection<RenterInformationEntity> renterInformationsByCityId) {
        this.renterInformationsByCityId = renterInformationsByCityId;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<WarehouseEntity> getWarehousesByCityId() {
        return warehousesByCityId;
    }

    public void setWarehousesByCityId(Collection<WarehouseEntity> warehousesByCityId) {
        this.warehousesByCityId = warehousesByCityId;
    }
}
