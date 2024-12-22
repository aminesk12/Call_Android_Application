package esisa.ac.ma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import esisa.ac.ma.R;
import esisa.ac.ma.entities.Favorite;
import esisa.ac.ma.services.Managerfavorite;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private List<Favorite> favorites = new ArrayList<>();
    private List<Favorite> cache = new ArrayList<>();
    private Managerfavorite managerfavorite;
    private ContactAdapter contactAdapter;

    public FavoriteAdapter(Context context) {
        managerfavorite = new Managerfavorite(context);
        favorites = managerfavorite.getAll();
        cache.addAll(favorites);
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        TextView smsCount;
        TextView callCount;
        ImageView RemoveFav;


        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.favorite_name);
            phoneTextView = itemView.findViewById(R.id.favorite_adress);

            RemoveFav = itemView.findViewById(R.id.icon_btn_Fav);
        }
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite favorite = favorites.get(position);
        holder.nameTextView.setText(favorite.getContactName());
        holder.RemoveFav.setImageResource(R.drawable.icone2);
        holder.phoneTextView.setText(favorite.getContactPhone());


        holder.RemoveFav.setOnClickListener(v -> {
            // Remove the item from the list
            favorites.remove(position);
            managerfavorite.delete(favorite);

            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }
}
