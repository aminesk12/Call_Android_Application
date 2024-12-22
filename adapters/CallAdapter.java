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
import esisa.ac.ma.dao.Call_Dao;
import esisa.ac.ma.entities.Call;
import esisa.ac.ma.entities.SMS;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.Item> {

    private Vector<Call> model = new Vector<>();
    private Vector<Call> cacheData ;
    private Call_Dao callDao;

    public CallAdapter(Context context) {
        callDao = new Call_Dao(context);
        model = callDao.getVcalls();
        cacheData =  new Vector<>(model);

    }
    public void filter(String query) {
        Vector<Call> filteredList = new Vector<>();
        String lowerCaseQuery = query.toLowerCase();
        for (Call call : cacheData) {
            if (call.getContactPhone().toLowerCase().contains(lowerCaseQuery) || call.getContactName().toLowerCase().contains(lowerCaseQuery)) {
                filteredList.add(call);
            }
        }
        model = filteredList;
        notifyDataSetChanged();
    }
    class Item extends RecyclerView.ViewHolder {
        TextView contactName;
        TextView contactPhone;
        TextView date;

        public Item(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.caller_name);
            contactPhone = itemView.findViewById(R.id.caller_phone);
            date = itemView.findViewById(R.id.date_of_call);
        }
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Item(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        holder.contactName.setText(model.get(position).getContactName());
        holder.contactPhone.setText(model.get(position).getContactPhone());
        holder.date.setText(model.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
