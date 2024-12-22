package esisa.ac.ma.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import esisa.ac.ma.R;
import esisa.ac.ma.entities.SMS;

public class SMS_Detail_Adapter extends RecyclerView.Adapter<SMS_Detail_Adapter.SMSDetailViewHolder> {

    private final SMS sms;

    public SMS_Detail_Adapter(SMS sms) {
        this.sms = sms;
    }

    @NonNull
    @Override
    public SMSDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_details, parent, false);
        return new SMSDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SMSDetailViewHolder holder, int position) {
        holder.smsMessage.setText(sms.getSMSMessage().toString());  // Show all messages as a String
        holder.smsDate.setText(sms.getDate());
    }

    @Override
    public int getItemCount() {
        return (int) sms.getSMSCount();
    }

    static class SMSDetailViewHolder extends RecyclerView.ViewHolder {
        TextView smsMessage;
        TextView smsDate;

        public SMSDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            smsMessage = itemView.findViewById(R.id.sms_message);
            smsDate = itemView.findViewById(R.id.sms_date);
        }
    }
}

