package esisa.ac.ma.views;

import android.Manifest;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import esisa.ac.ma.R;
import esisa.ac.ma.adapters.CallAdapter;

public class CallFragement extends Fragment {
    private ActivityResultLauncher<String> launcher;
    private RecyclerView recyclerView;
    private CallAdapter callAdapter;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.call_fragement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.caller_recycler);
        searchView = view.findViewById(R.id.search_View);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCalls(newText);
                return true;
            }
        });
        launcher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        showCalls();
                    }
                });
        launcher.launch(Manifest.permission.READ_CALL_LOG);
    }

    private void showCalls() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            callAdapter = new CallAdapter(requireContext());
            recyclerView.setAdapter(callAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        }
    }
    private void filterCalls(String query) {
        if (callAdapter != null) {
            callAdapter.filter(query);
        }
    }
}

