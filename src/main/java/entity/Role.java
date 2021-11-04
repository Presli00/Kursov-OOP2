package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    private int roleId;
    private int subroleId;
    private String roleName;
    private SubRole subRoleBySubroleId;
    private Collection<User> usersByRoleId;

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "subrole_id")
    public int getSubroleId() {
        return subroleId;
    }

    public void setSubroleId(int subroleId) {
        this.subroleId = subroleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (subroleId != role.subroleId) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + subroleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "subrole_id", referencedColumnName = "Subrole_id", nullable = false)
    public SubRole getSubRoleBySubroleId() {
        return subRoleBySubroleId;
    }

    public void setSubRoleBySubroleId(SubRole subRoleBySubroleId) {
        this.subRoleBySubroleId = subRoleBySubroleId;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<User> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<User> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }
}
