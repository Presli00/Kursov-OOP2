package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Agents_list", schema = "warehouse")
public class AgentsList implements Serializable {
    @Id
    @Column(name = "AgentsList_id", nullable = false)
    private int agentsListId;
    @ManyToOne
    @JoinColumn(name = "Agent_id", nullable = false)
    private Agent agentId;
    @OneToMany
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Set<Warehouse> warehouseId;

    public int getAgentsListId() {
        return agentsListId;
    }

    public void setAgentsListId(int agentsListId) {
        this.agentsListId = agentsListId;
    }

    public Agent getAgentId() {
        return agentId;
    }

    public void setAgentId(Agent agentId) {
        this.agentId = agentId;
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
        if (agentId != that.agentId) return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "AgentsList{" +
                "agentsListId=" + agentsListId +
                ", agentId=" + agentId +
                ", warehouseId=" + warehouseId +
                '}';
    }
}
