package com.example.vadym.testtasknorthstudio.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vadym.testtasknorthstudio.R;
import com.example.vadym.testtasknorthstudio.model.DateModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vadym on 21.02.2018.
 */

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.timeText)
    TextView timeText;
    @BindView(R.id.dateText)
    TextView dateText;
    @BindView(R.id.messageText)
    TextView messageText;

    public NotificationViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void setInfo(DateModel model) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(model.getTimeMills());

        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        timeText.setText(itemView.getResources().getString(R.string.time_text, hours, minutes, seconds));
        dateText.setText(itemView.getResources().getString(R.string.date_text, ++month, dayOfMonth, year));
        messageText.setText(model.getMessageText());
    }
}
