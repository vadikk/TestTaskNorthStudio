package com.example.vadym.testtasknorthstudio.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.testtasknorthstudio.R;
import com.example.vadym.testtasknorthstudio.model.DateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 21.02.2018.
 */

public class NotifiactionRecyclerAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private List<DateModel> dateModels = new ArrayList<>();

    public NotifiactionRecyclerAdapter() {
    }

    public void add(DateModel model) {
        notifyItemInserted(getItemCount() - 1);

        dateModels.add(model);
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_layout, parent, false);

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        DateModel model = dateModels.get(position);
        if (model != null) {
            holder.setInfo(model);
        }
    }

    @Override
    public int getItemCount() {
        return dateModels.size();
    }

    public void clear() {
        notifyItemRangeRemoved(0, dateModels.size());
        dateModels.clear();
    }
}
