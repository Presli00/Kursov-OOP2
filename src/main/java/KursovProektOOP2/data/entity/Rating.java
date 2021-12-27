package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Rating", schema = "warehouse")
@Entity
public class Rating implements Serializable {
    @Id
    @Column(name = "idRating", nullable = false)
    private int idRating;
    @Column(name = "Rating", nullable = false)
    private double rating;
    @ManyToOne
    @JoinColumn(name = "idAgent", nullable = false)
    private Agent Agentobj;
    @ManyToOne
    @JoinColumn(name = "idOwner", nullable = false)
    private Owner idOwner;

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Agent getIdAgent() {
        return Agentobj;
    }

    public void setIdAgent(Agent idAgent) {
        this.Agentobj = idAgent;
    }

    public Owner getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Owner idOwner) {
        this.idOwner = idOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return idRating == rating.idRating && rating.equals(rating.rating) && Agentobj.equals(rating.Agentobj) && idOwner.equals(rating.idOwner);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "idRating=" + idRating +
                ", rating=" + rating +
                ", idAgent=" + Agentobj +
                ", idOwner="+idOwner+
                '}';
    }
}
