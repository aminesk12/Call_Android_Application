package esisa.ac.ma.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import esisa.ac.ma.views.CallFragement;
import esisa.ac.ma.views.ContactFragment;


import esisa.ac.ma.views.FavoriteFragment;
import esisa.ac.ma.views.SMSFragment;


public class FragementAdapter extends FragmentStateAdapter {

    public FragementAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new ContactFragment();
            case 1 : return new SMSFragment();
            case 2 : return new CallFragement();
            case 3 : return new FavoriteFragment();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        // Return the total number of fragments
        return 4;
    }
}
