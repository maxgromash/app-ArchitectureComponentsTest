package com.example.componentstest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.componentstest.data.Title;
import com.example.componentstest.data.TitleDatabase;

import java.util.List;

public class TitleViewModel extends AndroidViewModel {
    private static TitleDatabase database;
    private LiveData<List<Title>> titleList;

    public TitleViewModel(@NonNull Application application) {
        super(application);
        database = TitleDatabase.getInstance(application.getApplicationContext());
        titleList = database.titleDao().getAllTitles();
    }

    public LiveData<List<Title>> getTitles() {
        return titleList;
    }

    public void addTitle(Title title) {
        new AddTitleAsyncTask().execute(title);
    }

    public void deleteTitle(int title) {
        new DeleteTitleByPosition().execute(title);
    }

    private class AddTitleAsyncTask extends AsyncTask<Title, Void, Void> {

        @Override
        protected Void doInBackground(Title... titles) {
            database.titleDao().addTitle(titles[0]);
            return null;
        }
    }

    private class DeleteTitleAsyncTask extends AsyncTask<Title, Void, Void> {
        @Override
        protected Void doInBackground(Title... titles) {
            database.titleDao().addTitle(titles[0]);
            return null;
        }
    }

    private class DeleteTitleByPosition extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            database.titleDao().deleteTitle(titleList.getValue().get(integers[0]));
            return null;
        }
    }
}
