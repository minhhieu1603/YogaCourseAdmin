package com.example.yogacourse.ui.manageclass;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yogacourse.AddClassActivity;
import com.example.yogacourse.Course;
import com.example.yogacourse.CourseAdapter;
import com.example.yogacourse.DBHelper;
import com.example.yogacourse.R;
import com.example.yogacourse.YogaClass;
import com.example.yogacourse.YogaClassAdapter;
import com.example.yogacourse.databinding.FragmentHomeBinding;
import com.example.yogacourse.databinding.FragmentManageClassBinding;

import java.util.ArrayList;
//import com.example.yogacourse.ui.home.HomeViewModel;

public class ManageClassFragment extends Fragment {

    private FragmentManageClassBinding binding;
    Button addBtn;
    private DBHelper dbHelper;
    private RecyclerView recyclerView;
    private YogaClassAdapter yogaClassAdapter;
    private ArrayList<YogaClass> yogaClassList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentManageClassBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        addBtn = binding.addClass;
        // Khởi tạo RecyclerView và Adapter
        recyclerView = binding.recyclerView;
        dbHelper = new DBHelper(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(yogaClassAdapter);
        LoadClasses();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddClassActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Logic load lại dữ liệu tại đây
        LoadClasses(); // Gọi hàm load dữ liệu của bạn
    }
    public void LoadClasses(){
        yogaClassList = dbHelper.getAllClasses();
        yogaClassAdapter = new YogaClassAdapter(getContext(), yogaClassList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(yogaClassAdapter);
    }
}