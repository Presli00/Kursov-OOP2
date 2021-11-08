package KursovProektOOP2.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "rental_history", schema = "warehouse")
@Entity
public class RentalHistory implements Serializable {
    @Id
    @Column(name = "History_id", nullable = false)
    private int historyId;
    @OneToOne
    @JoinColumn(name = "Formular_id", nullable = false)
    private Formular formularId;

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Formular getFormularId() {
        return formularId;
    }

    public void setFormularId(Formular formularId) {
        this.formularId = formularId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalHistory that = (RentalHistory) o;

        if (historyId != that.historyId) return false;
        if (formularId != that.formularId) return false;

        return true;
    }

    @Override
    public String toString() {
        return "RentalHistory{" +
                "historyId=" + historyId +
                ", formularId=" + formularId +
                '}';
    }
}
