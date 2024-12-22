package esisa.ac.ma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import esisa.ac.ma.R;
import esisa.ac.ma.dao.ContactDao;
import esisa.ac.ma.entities.Contact;
import esisa.ac.ma.entities.Favorite;
import esisa.ac.ma.services.Managerfavorite;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Item> {

    private List<Contact> model = new ArrayList<>();
    private List<Contact> cache = new ArrayList<>();
    private Managerfavorite managerfavorite;
    private ContactDao contactDao;
    private Context context;


    public ContactAdapter(Context context) {
        this.context = context;
        contactDao = new ContactDao(context);
        model = contactDao.getVcontact();
        managerfavorite = new Managerfavorite(context);
        cache.addAll(model);
    }

    class Item extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        TextView phones;
        ImageView addToFavoritesButton;
        Button      load_btn ;


        public Item(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            date = itemView.findViewById(R.id.date_of_creation);
            phones = itemView.findViewById(R.id.contact_phone);
            addToFavoritesButton = itemView.findViewById(R.id.icon_btn);
            load_btn = itemView.findViewById(R.id.down_button);

        }
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Item(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Contact contact = model.get(position);
        String firstName = contact.getFirstName();
        holder.name.setText(contact.getFirstName());
        holder.date.setText(contact.getDate());

        StringBuilder phonesBuilder = new StringBuilder();
        for (String phone : contact.getPhones()) {
            phonesBuilder.append(phone);
        }

        holder.phones.setText(phonesBuilder.toString());
        System.out.println("Name -> " + Search_For_Fav(contact));


        if (holder.addToFavoritesButton != null) {
            holder.addToFavoritesButton.setImageResource(Search_For_Fav(contact) ? R.drawable.icone2 : R.drawable.icones);
            holder.addToFavoritesButton.setOnClickListener(v -> {
                if(Search_For_Fav(contact)){
                    managerfavorite.deleteByName(contact.getFirstName());
                    holder.addToFavoritesButton.setImageResource(R.drawable.icones);
                }
                else{
                    managerfavorite.addToFavorites(contact);
                    holder.addToFavoritesButton.setImageResource(R.drawable.icone2);
                }
            });
        }


    }




    @Override
    public int getItemCount() {
        return model.size();
    }

    public void filter(String query) {
        model.clear();
        if (query.isEmpty()) {
            model.addAll(cache);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Contact contact : cache) {
                if (contact.getFirstName().toLowerCase().contains(lowerCaseQuery)) {
                    model.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }
    public Boolean Search_For_Fav(Contact contact){

        List<Favorite> allFavorites = managerfavorite.getAll();
        for (Favorite existingFavorite : allFavorites) {
            if (existingFavorite.getContactName().equals(contact.getFirstName())) {
                return true; // Found the favorite, return true
            }
        }
        return false; // Favorite not found, return false
    }
}
