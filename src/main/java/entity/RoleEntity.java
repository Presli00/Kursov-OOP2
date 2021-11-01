package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role", schema = "warehouse", catalog = "")
public class RoleEntity {
    private int roleId;
    private int subroleId;
    private String roleName;
    private SubRoleEntity subRoleBySubroleId;
    private Collection<UserEntity> usersByRoleId;

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

        RoleEntity that = (RoleEntity) o;

        if (roleId != that.roleId) return false;
        if (subroleId != that.subroleId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

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
    public SubRoleEntity getSubRoleBySubroleId() {
        return subRoleBySubroleId;
    }

    public void setSubRoleBySubroleId(SubRoleEntity subRoleBySubroleId) {
        this.subRoleBySubroleId = subRoleBySubroleId;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<UserEntity> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<UserEntity> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }
}
