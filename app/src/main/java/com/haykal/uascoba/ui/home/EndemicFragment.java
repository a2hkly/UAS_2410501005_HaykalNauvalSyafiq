package com.haykal.uascoba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.haykal.uascoba.adapter.EndemicAdapter;
import com.haykal.uascoba.databinding.FragmentEndemicBinding;
import com.haykal.uascoba.model.EndemicEntity;
import com.haykal.uascoba.repository.EndemicRepository;
import com.haykal.uascoba.ui.detail.DetailActivity;

public class EndemicFragment extends Fragment {

    private static final String ARG_TYPE = "type";
    private String type;
    private FragmentEndemicBinding binding;
    private EndemicRepository repository;
    private EndemicAdapter adapter;

    public static EndemicFragment newInstance(String type) {
        EndemicFragment fragment = new EndemicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
        }
        repository = new EndemicRepository(requireActivity().getApplication());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentEndemicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new EndemicAdapter(endemic -> {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("ENDEMIC_ID", endemic.getId());
            intent.putExtra("ENDEMIC_NAME", endemic.getNama());
            startActivity(intent);
        });

        binding.rvEndemic.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvEndemic.setAdapter(adapter);

        repository.getEndemicByType(type).observe(getViewLifecycleOwner(), endemicEntities -> {
            adapter.setEndemicList(endemicEntities);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
