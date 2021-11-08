package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Role", schema = "warehouse")
@Entity
public class Role implements Serializable {
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @OneToOne
    @JoinColumn(name = "Subrole_id", nullable = false)
    private SubRole subroleId;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    public SubRole getSubroleId() {
        return subroleId;
    }

    public void setSubroleId(SubRole subroleId) {
        this.subroleId = subroleId;
    }

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
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", subroleId=" + subroleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
