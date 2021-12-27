package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name = "Agent", schema = "warehouse")
@Entity
public class Agent implements Serializable {
    @Id
    @Column(name = "idAgent", nullable = false)
    private int idAgent;
    @OneToOne
    @JoinColumn(name = "idFromUser", nullable = false)
    private User idFromUser;
    @Column(name = "dealAmount", nullable = false)
    private int dealAmount;
    @ManyToMany(mappedBy = "agentsId", fetch = FetchType.EAGER)
    private Set<Warehouse> warehouses;
    @OneToMany(mappedBy = "Agentobj", fetch = FetchType.EAGER)
    private Set<Rating> receivedRatings;

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public User getIdFromUser() {
        return idFromUser;
    }

    public void setIdFromUser(User idFromUser) {
        this.idFromUser = idFromUser;
    }

    public int getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(int dealAmount) {
        this.dealAmount = dealAmount;
    }

    public Set<Rating> getReceivedRatings() {
        return receivedRatings;
    }

    public void setReceivedRatings(Set<Rating> receivedRatings) {
        this.receivedRatings = receivedRatings;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return idAgent == agent.idAgent && dealAmount == agent.dealAmount && idFromUser.equals(agent.idFromUser);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "idAgent=" + idAgent +
                ", idFromUser=" + idFromUser +
                ", dealAmount=" + dealAmount +
                '}';
    }
}

