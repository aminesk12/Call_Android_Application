package esisa.ac.ma.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import esisa.ac.ma.entities.Call;


public class Call_Dao {


        private final Vector<Call> vcalls = new Vector<>();
        private final Context ctx;
        private SimpleDateFormat simpleDateFormat;




    @SuppressLint("SimpleDateFormat")
    public Call_Dao(Context ctx) {
        this.ctx = ctx;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public Vector<Call> getVcalls() {
            loadCallLogs();
            return vcalls;
        }
    public void loadCallLogs()    {
        try (Cursor cursor = ctx.getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                CallLog.Calls.DATE + " DESC"
        )) {

            if (cursor != null && cursor.moveToFirst()) {
                int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
                int date = cursor.getColumnIndex(CallLog.Calls.DATE);
                Call call;
                do {
                    call = new Call();
                    String phoneNumber = cursor.getString(number);
                    String contactName = cursor.getString(name);
                    long callDate = cursor.getLong(date);

                    // Convert callDate to a human-readable date format
                    String formattedDate = simpleDateFormat.format(new Date(callDate));

                    call.setContactName(contactName);
                    call.setContactPhone(phoneNumber);
                    call.setDate(formattedDate);

                    vcalls.add(call);
                } while (cursor.moveToNext());
            }
        }
        }
    }


