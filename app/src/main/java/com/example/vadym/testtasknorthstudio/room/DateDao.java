package com.example.vadym.testtasknorthstudio.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vadym.testtasknorthstudio.model.DateModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Vadym on 21.02.2018.
 */
@Dao
public interface DateDao {

    @Insert(onConflict = REPLACE)
    void insert(DateModel date);

    @Query("DELETE FROM dateModel")
    void deleteAll();

    @Query("SELECT*FROM dateModel")
    LiveData<List<DateModel>> getAll();
}
