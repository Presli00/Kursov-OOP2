package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.util.Objects;

@Table(name="Agent",schema = "warehouse")
@Entity
public class Agent {
    @Id
    @Column(name = "idAgent", nullable = false)
    private int idAgent;
    @Column(name = "rating", nullable = false)
    private double rating;
    @Column(name = "dealAmount", nullable = false)
    private int dealAmount;
    @OneToOne
    @JoinColumn(name="idFromUser", nullable = false)
    private User userId;

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return idAgent == agent.idAgent && Objects.equals(rating, agent.rating) && Objects.equals(dealAmount, agent.dealAmount) && userId.equals(agent.userId);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "idAgent=" + idAgent +
                ", rating=" + rating +
                ", dealAmount=" + dealAmount +
                ", userId=" + userId +
                '}';
    }
}
