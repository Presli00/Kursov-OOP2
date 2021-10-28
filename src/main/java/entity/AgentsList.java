package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "agents_list", schema = "warehouse", catalog = "")
public class AgentsList {
    private int agentsListId;
    private int userId;
    private int warehouseId;
    private User userByUserId;
    private Warehouse warehouseByWarehouseId;
    private Collection<Warehouse> warehousesByAgentsListId;

    @Id
    @Column(name = "AgentsList_id")
    public int getAgentsListId() {
        return agentsListId;
    }

    public void setAgentsListId(int agentsListId) {
        this.agentsListId = agentsListId;
    }

    @Basic
    @Column(name = "User_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

        AgentsList that = (AgentsList) o;

        if (agentsListId != that.agentsListId) return false;
        if (userId != that.userId) return false;
        if (warehouseId != that.warehouseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = agentsListId;
        result = 31 * result + userId;
        result = 31 * result + warehouseId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "User_id", referencedColumnName = "User_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "Warehouse_id", referencedColumnName = "Warehouse_id", nullable = false)
    public Warehouse getWarehouseByWarehouseId() {
        return warehouseByWarehouseId;
    }

    public void setWarehouseByWarehouseId(Warehouse warehouseByWarehouseId) {
        this.warehouseByWarehouseId = warehouseByWarehouseId;
    }

    @OneToMany(mappedBy = "agentsListByAgentSId")
    public Collection<Warehouse> getWarehousesByAgentsListId() {
        return warehousesByAgentsListId;
    }

    public void setWarehousesByAgentsListId(Collection<Warehouse> warehousesByAgentsListId) {
        this.warehousesByAgentsListId = warehousesByAgentsListId;
    }
}
