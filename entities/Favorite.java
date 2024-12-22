package esisa.ac.ma.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Vector;

@Entity(tableName = "Favorites_Table")
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String contactName;
    private String contactPhone;
    private long callCount;
    private long smsCount;

    public Favorite() {
    }

    public Favorite(String contactName,String contactPhone ,long callCount, long smsCount) {
        this.contactName = contactName;
        this.callCount = callCount;
        this.smsCount = smsCount;
        this.contactPhone = contactPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getCallCount() {
        return callCount;
    }

    public void setCallCount(long callCount) {
        this.callCount = callCount;
    }

    public long getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(long smsCount) {
        this.smsCount = smsCount;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", callCount=" + callCount +
                ", smsCount=" + smsCount +
                '}';
    }
}
