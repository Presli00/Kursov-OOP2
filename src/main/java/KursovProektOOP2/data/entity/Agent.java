package KursovProektOOP2.data.entity;

import javax.persistence.*;

@Table(name="Agent",schema = "warehouse")
@Entity
public class Agent {
    @Id
    @Column(name = "idAgent", nullable = false)
    private int idAgent;
    @Column(name = "rating", nullable = false)
    private Object rating;
    @Column(name = "dealAmount", nullable = false)
    private Integer dealAmount;
    @OneToOne
    @JoinColumn(name="idFromUser", nullable = false)
    private User userId;

    public int getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(int idAgent) {
        this.idAgent = idAgent;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Integer getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Integer dealAmount) {
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
        return idAgent == agent.idAgent && rating.equals(agent.rating) && dealAmount.equals(agent.dealAmount) && userId.equals(agent.userId);
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
