package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
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
    @Column(name = "rating", nullable = false)
    private double rating;
    @Column(name = "dealAmount", nullable = false)
    private int dealAmount;
    @ManyToMany
    private Set<Warehouse> warehouses;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(int dealAmount) {
        this.dealAmount = dealAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return idAgent == agent.idAgent && Double.compare(agent.rating, rating) == 0 && dealAmount == agent.dealAmount && idFromUser.equals(agent.idFromUser);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "idAgent=" + idAgent +
                ", idFromUser=" + idFromUser +
                ", rating=" + rating +
                ", dealAmount=" + dealAmount +
                '}';
    }
}

