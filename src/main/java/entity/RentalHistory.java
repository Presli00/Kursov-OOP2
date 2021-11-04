package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rental_history", schema = "warehouse", catalog = "")
public class RentalHistory {
    private int historyId;
    private int formularId;
    private Formular formularByFormularId;
    private Collection<StorageRoom> storageRoomsByHistoryId;

    @Id
    @Column(name = "History_id")
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    @Basic
    @Column(name = "Formular_id")
    public int getFormularId() {
        return formularId;
    }

    public void setFormularId(int formularId) {
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
    public int hashCode() {
        int result = historyId;
        result = 31 * result + formularId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Formular_id", referencedColumnName = "Formular_id", nullable = false)
    public Formular getFormularByFormularId() {
        return formularByFormularId;
    }

    public void setFormularByFormularId(Formular formularByFormularId) {
        this.formularByFormularId = formularByFormularId;
    }

    @OneToMany(mappedBy = "rentalHistoryByHistoryId")
    public Collection<StorageRoom> getStorageRoomsByHistoryId() {
        return storageRoomsByHistoryId;
    }

    public void setStorageRoomsByHistoryId(Collection<StorageRoom> storageRoomsByHistoryId) {
        this.storageRoomsByHistoryId = storageRoomsByHistoryId;
    }
}
