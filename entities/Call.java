package esisa.ac.ma.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Call_Table")
public class Call {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String contactName;
    private String contactPhone;
    private String date;

    public Call() {

    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Call{" +
                "contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
