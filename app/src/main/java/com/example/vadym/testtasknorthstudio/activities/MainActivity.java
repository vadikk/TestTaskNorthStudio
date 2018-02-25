package com.example.vadym.testtasknorthstudio.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.vadym.testtasknorthstudio.R;
import com.example.vadym.testtasknorthstudio.model.DateModel;
import com.example.vadym.testtasknorthstudio.recycler.NotifiactionRecyclerAdapter;
import com.example.vadym.testtasknorthstudio.room.DateListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.addElement)
    Button addButton;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private NotifiactionRecyclerAdapter adapter;
    private DateListModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(DateListModel.class);

        ButterKnife.bind(this);
        addButton.setOnClickListener(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new NotifiactionRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        //viewModel.deleteALL();
        subscribeUI();
    }

    private void subscribeUI() {
        viewModel.getItems().observe(MainActivity.this, new Observer<List<DateModel>>() {
            @Override
            public void onChanged(@Nullable List<DateModel> dateModels) {

                if (dateModels == null)
                    return;

                adapter.clear();

                for (int i = 0; i < dateModels.size(); i++) {
                    DateModel model = dateModels.get(i);
                    adapter.add(model);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddElementActivity.class);
        startActivity(intent);
    }
}
