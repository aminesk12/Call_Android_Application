package esisa.ac.ma.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import androidx.loader.content.CursorLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Contacts;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import esisa.ac.ma.entities.Contact;

public class   ContactDao {
    private final Vector<Contact> vcontact = new Vector<>();
    private final Context ctx;
    private final FirebaseFirestore db;
    private SimpleDateFormat simpleDateFormat;
    public ContactDao(Context ctx) {
        this.ctx = ctx;
        db = FirebaseFirestore.getInstance();
        load();
    }
    public Vector<Contact> getVcontact() {
        return vcontact;
    }
    public void load() {
        try (Cursor contacts = new CursorLoader(ctx, Contacts.CONTENT_URI, null,
                null, null, null).loadInBackground()) {
            Contact c;
            if (contacts != null && contacts.getCount() != 0) {
                while (contacts.moveToNext()) {
                    c = new Contact();
                    @SuppressLint("Range") String id = contacts.getString(contacts.getColumnIndex(Contacts._ID));
                    @SuppressLint("Range") String name = contacts.getString(contacts.getColumnIndex(Contacts.DISPLAY_NAME));
                    //date in ms
                    @SuppressLint("Range") long date = contacts.getLong(contacts.getColumnIndex(Contacts.CONTACT_LAST_UPDATED_TIMESTAMP));
                    c.setFirstName(name);
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


                    c.setDate(simpleDateFormat.format(new Date(date)));
                    c.setPhones(getPhones(id));
                    c.setEmails(getEmails(id));
                    vcontact.add(c);
                }
            }
        }
    }
    public Vector<String> getPhones(String contact_id){
        Vector<String> phones = new Vector<>();
        String[] phone = {Phone.NUMBER};
        try (Cursor cursor = ctx.getContentResolver().query(Phone.CONTENT_URI,
                phone, contact_id + "=" + Phone.CONTACT_ID, null, null)) {
            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    phones.add(cursor.getString(0));
                }
            }
        }
        return phones;
    }
    public Vector<String> getEmails(String contact_id){
        Vector<String> emails = new Vector<>();
        String[] email = {Email.ADDRESS};
        try (Cursor cursor = ctx.getContentResolver().query(Email.CONTENT_URI,
                email, contact_id + "=" +Email.CONTACT_ID, null, null)) {
            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    emails.add(cursor.getString(0));
                }
            }
        }
        return emails;
    }
    public void addContact(Contact contact) {
        db.collection("contacts")
                .add(contact)
                .addOnSuccessListener(documentReference -> {
                    // Contact added successfully
                })
                .addOnFailureListener(e -> {
                    // Error adding contact
                });
    }
    public void loadContacts() {
        db.collection("contacts")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    // Handle document snapshots
                    for (DocumentSnapshot document : queryDocumentSnapshots) {
                        // Convert document to Contact object and add to vcontact
                        vcontact.add(document.toObject(Contact.class));
                    }
                })
                .addOnFailureListener(e -> {
                    // Error fetching contacts
                });
    }


}



