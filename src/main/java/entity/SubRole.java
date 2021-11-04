package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sub_role", schema = "warehouse", catalog = "")
public class SubRole {
    private int subroleId;
    private String subroleName;
    private Collection<Role> rolesBySubroleId;

    @Id
    @Column(name = "Subrole_id")
    public int getSubroleId() {
        return subroleId;
    }

    public void setSubroleId(int subroleId) {
        this.subroleId = subroleId;
    }

    @Basic
    @Column(name = "Subrole_name")
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
    public int hashCode() {
        int result = subroleId;
        result = 31 * result + (subroleName != null ? subroleName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "subRoleBySubroleId")
    public Collection<Role> getRolesBySubroleId() {
        return rolesBySubroleId;
    }

    public void setRolesBySubroleId(Collection<Role> rolesBySubroleId) {
        this.rolesBySubroleId = rolesBySubroleId;
    }
}
