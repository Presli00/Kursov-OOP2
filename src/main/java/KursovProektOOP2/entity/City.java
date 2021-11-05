package KursovProektOOP2.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="City", schema = "warehouse")
@Entity
public class City implements Serializable {
    @Id
    @Column(name = "City_id", nullable = false)
    private int cityId;
    @Column(name = "City", nullable = false)
    private String city;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

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
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", city='" + city + '\'' +
                '}';
    }
}
