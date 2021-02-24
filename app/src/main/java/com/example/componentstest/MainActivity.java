package com.example.componentstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.componentstest.data.Title;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TitleViewModel titleViewModel;
    private TitleAdapter adapter;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        RecyclerView recyclerView = findViewById(R.id.recyclerTitles);
        adapter = new TitleAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        titleViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(TitleViewModel.class))
                    return (T) new TitleViewModel(getApplication());
                else {
                    throw new IllegalArgumentException();
                }
            }
        }).get(TitleViewModel.class);

        LiveData<List<Title>> filmsFromLiveData = titleViewModel.getTitles();;
        filmsFromLiveData.observe(this, titles -> {
            adapter.setTitles(titles);
            adapter.notifyDataSetChanged();
        });

        adapter.setOnItemClickListener(position -> titleViewModel.deleteTitle(position));
    }

    public void onClickAdd(View view) {
        titleViewModel.addTitle(new Title(editText.getText().toString()));
        editText.setText("");
    }
}