package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name="Agents_list", schema = "warehouse")
@Entity
public class AgentsList implements Serializable {
    @Id
    @Column(name = "AgentsList_id", nullable = false)
    private int agentsListId;
    @OneToOne
    @JoinColumn(name = "User_id", nullable = false)
    private User userId;
    @OneToMany
    @JoinColumn(name = "Warehouse_id", nullable = false)
    private Set<Warehouse> warehouseId;

    public int getAgentsListId() {
        return agentsListId;
    }

    public void setAgentsListId(int agentsListId) {
        this.agentsListId = agentsListId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Set<Warehouse> getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Set<Warehouse> warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgentsList that = (AgentsList) o;

        if (agentsListId != that.agentsListId) return false;
        if (userId != that.userId) return false;
        if (warehouseId != that.warehouseId) return false;
        return true;
    }

    @Override
    public String toString() {
        return "AgentsList{" +
                "agentsListId=" + agentsListId +
                ", userId=" + userId +
                ", warehouseId=" + warehouseId +
                '}';
    }
}
