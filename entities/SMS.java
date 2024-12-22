package esisa.ac.ma.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Vector;

@Entity(tableName = "SMS_Table")
public class SMS {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String Contact_name;
    private String adress;
    private Vector<String> SMSMessage;
    private long SMSCount;
    private String date;

    public SMS() {
        this.SMSMessage = new Vector<>();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Vector<String> getSMSMessage() {
        return SMSMessage;
    }

    public void setSMSMessage(Vector<String> SMSMessage) {
        this.SMSMessage = SMSMessage;
    }

    public String getContact_name() {
        return Contact_name;
    }

    public void setContact_name(String contact_name) {
        Contact_name = contact_name;
    }

    public long getSMSCount() {
        return SMSCount;
    }

    public void setSMSCount(long SMSCount) {
        this.SMSCount = SMSCount;
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
        return "SMS{" +
                "id=" + id +
                ", Contact_name='" + Contact_name + '\'' +
                ", adress='" + adress + '\'' +
                ", SMSMessage=" + SMSMessage +
                ", SMSCount=" + SMSCount +
                ", date='" + date + '\'' +
                '}';
    }
}
