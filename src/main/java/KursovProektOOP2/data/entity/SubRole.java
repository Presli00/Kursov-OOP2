package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Sub_Role", schema = "warehouse")
@Entity
public class SubRole implements Serializable {
    @Id
    @Column(name = "Subrole_id", nullable = false)
    private int subroleId;
    @Column(name = "Subrole_name", nullable = false)
    private String subroleName;


    public int getSubroleId() {
        return subroleId;
    }

    public void setSubroleId(int subroleId) {
        this.subroleId = subroleId;
    }


    public String getSubroleName() {
        return subroleName;
    }

    public void setSubroleName(String subroleName) {
        this.subroleName = subroleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubRole subRole = (SubRole) o;

        if (subroleId != subRole.subroleId) return false;
        if (subroleName != null ? !subroleName.equals(subRole.subroleName) : subRole.subroleName != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "SubRole{" +
                "subroleId=" + subroleId +
                ", subroleName='" + subroleName + '\'' +
                '}';
    }
}
