package esisa.ac.ma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import esisa.ac.ma.R;
import esisa.ac.ma.dao.SMSDao;
import esisa.ac.ma.entities.SMS;

public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.SMSViewHolder> {

    private static Vector<SMS> dataModel = new Vector<>();
    private Vector<SMS> cacheData = new Vector<>();
    private Context context;
    private ClickListener clickListener;

    public SMSAdapter(Context ctx) {
        dataModel = new SMSDao(ctx).getVsms();
        cacheData = new Vector<>(dataModel);
        this.context = ctx;
    }

    public interface ClickListener {
        void onClick(SMS data);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static Vector<SMS> getDataModel() {
        return dataModel;
    }

    public void filter(String query) {
        Vector<SMS> filteredList = new Vector<>();
        String lowerCaseQuery = query.toLowerCase();
        for (SMS sms : cacheData) {
            if (sms.getAdress().toLowerCase().contains(lowerCaseQuery)) {
                filteredList.add(sms);
            }
        }
        dataModel = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SMSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms, parent, false);
        return new SMSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SMSViewHolder holder, int position) {
        SMS sms = dataModel.get(position);
        String smscount = "" + sms.getSMSCount();
        if(sms.getContact_name() == null){
            holder.smsAdress2.setText(sms.getAdress());
            holder.smsMessage.setText(sms.getSMSMessage().firstElement());
            holder.smsDate.setText(sms.getDate());
            holder.smsCount.setText(smscount); // Bind SMS count
        }
        else{
            holder.contact_name.setText(sms.getContact_name());
            holder.smsMessage.setText(sms.getSMSMessage().firstElement());
            holder.smsDate.setText(sms.getDate());
            holder.smsCount.setText(smscount); // Bind SMS count
        }

        holder.itemView.setOnClickListener(v -> {

            System.out.println("DataModel  ->  " + dataModel);
        });
    }




    @Override
    public int getItemCount() {
        return dataModel.size();
    }

    class SMSViewHolder extends RecyclerView.ViewHolder {
        TextView smsAdress;
        TextView smsAdress2;
        TextView smsMessage;
        TextView smsDate;
        TextView contact_name;
        TextView smsCount;

        public SMSViewHolder(@NonNull View itemView) {
            super(itemView);

            smsAdress2 = itemView.findViewById(R.id.sms_adress2);
            smsMessage = itemView.findViewById(R.id.sms_message);
            smsDate = itemView.findViewById(R.id.sms_date);
            contact_name = itemView.findViewById(R.id.contact_sms);
            smsCount = itemView.findViewById(R.id.sms_count);

        }
    }
}
