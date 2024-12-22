package esisa.ac.ma.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import esisa.ac.ma.entities.SMS;import android.provider.ContactsContract;

public class SMSDao {
    private final Vector<SMS> vsms = new Vector<>();
    private final Context ctx;
    private final SimpleDateFormat simpleDateFormat;

    @SuppressLint("SimpleDateFormat")
    public SMSDao(Context ctx) {
        this.ctx = ctx;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Vector<SMS> getVsms() {
        loadSMSMessages();
        return vsms;
    }




    public void loadSMSMessages() {
        vsms.clear(); // Clear the vector before loading new messages
        try (Cursor cursor = ctx.getContentResolver().query(
                Uri.parse("content://sms/"),
                null,
                null,
                null,
                Telephony.Sms.DATE + " DESC"
        )) {
            if (cursor != null && cursor.moveToFirst()) {
                int bodyIndex = cursor.getColumnIndex(Telephony.Sms.BODY);
                int addressIndex = cursor.getColumnIndex(Telephony.Sms.ADDRESS);
                int dateIndex = cursor.getColumnIndex(Telephony.Sms.DATE);
                do {
                    String smsBody = cursor.getString(bodyIndex);
                    String smsAddress = cursor.getString(addressIndex);
                    long smsDate = cursor.getLong(dateIndex);

                    // Fetch contact name from contacts database
                    String contactName = getContactName(smsAddress);

                    // Check if an SMS with the same address exists in vsms
                    boolean exists = false;
                    for (SMS sms : vsms) {
                        if (sms.getAdress().equals(smsAddress)) {
                            exists = true;
                            sms.getSMSMessage().add(smsBody);// Add message to the existing SMS
                            sms.setSMSCount(sms.getSMSCount() + 1); // Increment the SMS counter
                            break;
                        }
                    }

                    // If the SMS doesn't exist, create a new SMS object
                    if (!exists) {
                        SMS sms = new SMS();
                        sms.setAdress(smsAddress);
                        sms.getSMSMessage().add(smsBody);
                        sms.setDate(simpleDateFormat.format(new Date(smsDate)));
                        sms.setSMSCount(1); // Set counter to 1
                        sms.setContact_name(contactName); // Set the contact name
                        vsms.add(sms);
                    }

                } while (cursor.moveToNext());
            }
        }
    }

    // Method to fetch contact name from contacts database
    @SuppressLint("Range")
    private String getContactName(String phoneNumber) {
        String contactName = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        try (Cursor cursor = ctx.getContentResolver().query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactName;
    }

}
