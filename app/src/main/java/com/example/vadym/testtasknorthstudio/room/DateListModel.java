package com.example.vadym.testtasknorthstudio.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.vadym.testtasknorthstudio.model.DateModel;

import java.util.List;

/**
 * Created by Vadym on 21.02.2018.
 */

public class DateListModel extends AndroidViewModel {

    private final LiveData<List<DateModel>> listLiveData;
    private DateBD db;

    public DateListModel(@NonNull Application application) {
        super(application);

        db = DateBD.getInstance(this.getApplication());
        listLiveData = db.dateDao().getAll();
    }

    public LiveData<List<DateModel>> getItems() {
        return listLiveData;
    }

    public void insertItem(DateModel model) {
        new insertAsyncTask(db).execute(model);
    }

    public void deleteALL() {
        new deleteAllAsyncTask(db).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private DateBD db;

        public deleteAllAsyncTask(DateBD db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            db.dateDao().deleteAll();
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<DateModel, Void, Void> {

        private DateBD db;

        public insertAsyncTask(DateBD db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(DateModel... dateModels) {

            for (DateModel model : dateModels) {
                db.dateDao().insert(model);
                return null;
            }
            return null;
        }
    }
}
