package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "agents_list", schema = "warehouse", catalog = "")
public class AgentsListEntity {
    private int agentsListId;
    private int agentId;
    private int warehouseId;
    private UserEntity userByAgentId;
    private WarehouseEntity warehouseByWarehouseId;
    private Collection<WarehouseEntity> warehousesByAgentsListId;

    @Id
    @Column(name = "AgentsList_id")
    public int getAgentsListId() {
        return agentsListId;
    }

    public void setAgentsListId(int agentsListId) {
        this.agentsListId = agentsListId;
    }

    @Basic
    @Column(name = "Agent_id")
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    @Basic
    @Column(name = "Warehouse_id")
    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgentsListEntity that = (AgentsListEntity) o;

        if (agentsListId != that.agentsListId) return false;
        if (agentId != that.agentId) return false;
        if (warehouseId != that.warehouseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = agentsListId;
        result = 31 * result + agentId;
        result = 31 * result + warehouseId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Agent_id", referencedColumnName = "User_id", nullable = false)
    public UserEntity getUserByAgentId() {
        return userByAgentId;
    }

    public void setUserByAgentId(UserEntity userByAgentId) {
        this.userByAgentId = userByAgentId;
    }

    @ManyToOne
    @JoinColumn(name = "Warehouse_id", referencedColumnName = "Warehouse_id", nullable = false)
    public WarehouseEntity getWarehouseByWarehouseId() {
        return warehouseByWarehouseId;
    }

    public void setWarehouseByWarehouseId(WarehouseEntity warehouseByWarehouseId) {
        this.warehouseByWarehouseId = warehouseByWarehouseId;
    }

    @OneToMany(mappedBy = "agentsListByAgentSId")
    public Collection<WarehouseEntity> getWarehousesByAgentsListId() {
        return warehousesByAgentsListId;
    }

    public void setWarehousesByAgentsListId(Collection<WarehouseEntity> warehousesByAgentsListId) {
        this.warehousesByAgentsListId = warehousesByAgentsListId;
    }
}
