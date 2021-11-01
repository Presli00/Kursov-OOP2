package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rental_history", schema = "warehouse", catalog = "")
public class RentalHistoryEntity {
    private int historyId;
    private int formularId;
    private FormularEntity formularByFormularId;
    private Collection<StorageRoomEntity> storageRoomsByHistoryId;

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

        RentalHistoryEntity that = (RentalHistoryEntity) o;

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
    public FormularEntity getFormularByFormularId() {
        return formularByFormularId;
    }

    public void setFormularByFormularId(FormularEntity formularByFormularId) {
        this.formularByFormularId = formularByFormularId;
    }

    @OneToMany(mappedBy = "rentalHistoryByHistoryId")
    public Collection<StorageRoomEntity> getStorageRoomsByHistoryId() {
        return storageRoomsByHistoryId;
    }

    public void setStorageRoomsByHistoryId(Collection<StorageRoomEntity> storageRoomsByHistoryId) {
        this.storageRoomsByHistoryId = storageRoomsByHistoryId;
    }
}
