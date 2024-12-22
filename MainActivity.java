package esisa.ac.ma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.FirebaseApp;

import esisa.ac.ma.adapters.FragementAdapter;
import esisa.ac.ma.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private FragementAdapter fragementAdapter;
    private TabLayout tabLayout;
    private static String[] TITLES = {"Contact","SMS","Call","Favorite"};
    private static int[] ICONS = {R.drawable.contact_icon, R.drawable.sms_icon, R.drawable.call_icon, R.drawable.fav_icon}; // Add your icon drawables here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewPager2 = binding.viewPager2;
        tabLayout = binding.tableLayout;
        fragementAdapter = new FragementAdapter(this);
        viewPager2.setAdapter(fragementAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(TITLES[position]);
                tab.setIcon(ICONS[position]); // Set icon for each tab
            }
        }).attach();

        // Initialize Firebase
FirebaseApp.initializeApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_load, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case 1:
                // Handle menu item 1 click
                return true;
            case 2:
                // Handle menu item 2 click
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
