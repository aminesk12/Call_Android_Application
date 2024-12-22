package esisa.ac.ma.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "Contact_Table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String FirstName;
    private List<String> Phones;
    private List<String> Emails;
    private String date;
    private String url;

    public Contact() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public List<String> getPhones() {
        return Phones;
    }

    public void setPhones(List<String> phones) {
        Phones = phones;
    }

    public List<String> getEmails() {
        return Emails;
    }

    public void setEmails(List<String> emails) {
        Emails = emails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", Phones=" + Phones +
                ", Emails=" + Emails +
                ", date='" + date + '\'' +
                '}';
    }
}
