package esisa.ac.ma.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import esisa.ac.ma.R;
import esisa.ac.ma.adapters.SMSAdapter;
import esisa.ac.ma.adapters.SMS_Detail_Adapter;
import esisa.ac.ma.dao.SMSDao;
import esisa.ac.ma.dao.SharedData;
import esisa.ac.ma.entities.SMS;

public class SMSFragment extends Fragment {
    private ActivityResultLauncher<String> launcher;
    private RecyclerView recyclerView;
    private SMSAdapter smsAdapter;
    private SMSDao smsDao;
    private SharedData sharedData;
    private SearchView searchView;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s_m_s, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedData = new ViewModelProvider(requireActivity()).get(SharedData.class);

        smsDao = new SMSDao(requireContext());
        recyclerView = view.findViewById(R.id.sms_recycler);
        searchView = view.findViewById(R.id.search_View);
        viewPager = view.findViewById(R.id.viewPager2);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterSMS(newText);
                return true;
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        showSMS();
                    }
                });
        launcher.launch(Manifest.permission.READ_SMS);
    }

    private void showSMS() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            smsAdapter = new SMSAdapter(requireActivity());

            recyclerView.setAdapter(smsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));


        }
    }



    private void filterSMS(String query) {
        if (smsAdapter != null) {
            smsAdapter.filter(query);
        }
    }
}
