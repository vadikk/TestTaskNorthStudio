package com.example.vadym.testtasknorthstudio.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vadym.testtasknorthstudio.R;
import com.example.vadym.testtasknorthstudio.model.DateModel;
import com.example.vadym.testtasknorthstudio.room.DateListModel;
import com.example.vadym.testtasknorthstudio.service.AlarmReceiver;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddElementActivity extends AppCompatActivity
        implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final int ID = 101;
    @BindView(R.id.timeButton)
    Button timeButton;
    @BindView(R.id.dateButton)
    Button dateButton;
    @BindView(R.id.addButton)
    Button addBtn;
    @BindView(R.id.editText)
    EditText editText;
    int hours, minutes, seconds, month, dayOfMonth, year = 0;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private DateListModel viewModel;
    private DateModel dateModel;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_element);

        viewModel = ViewModelProviders.of(this).get(DateListModel.class);
        dateModel = new DateModel();

        init();

        ButterKnife.bind(this);

        timeButton.setOnClickListener(this);
        dateButton.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    private void init() {
        calendar = Calendar.getInstance();
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        seconds = calendar.get(Calendar.SECOND);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.timeButton:
                setTimeDialog();
                break;
            case R.id.dateButton:
                setDateDialog();
                break;
            case R.id.addButton:
                goToNextActivity();
                break;
            default:
                break;
        }
    }

    private void goToNextActivity() {
        addToBD();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("message", dateModel.getMessageText());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ID, intent, PendingIntent.FLAG_ONE_SHOT);

        if (alarmManager != null)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        finish();
    }


    private void addToBD() {
        String text = editText.getText().toString();
        dateModel.setMessageText(text);

        calendar.set(year, month, dayOfMonth, hours, minutes, seconds);

        dateModel.setTimeMills(calendar.getTimeInMillis());
        viewModel.insertItem(dateModel);
        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: " + hourOfDay + "h " + minute + "m " + second;
        Log.d("TAG", "time " + time);
        hours = hourOfDay;
        minutes = minute;
        seconds = second;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: " + dayOfMonth + "/ " + (monthOfYear) + "/ " + year;
        Log.d("TAG", "date " + date);
        this.year = year;
        month = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }

    private void setTimeDialog() {
        Calendar now = Calendar.getInstance();

        timePickerDialog = TimePickerDialog.newInstance(
                AddElementActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), true);

        timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.d("TAG", "Dialog was cancelled");
            }
        });
        timePickerDialog.show(getFragmentManager(), "Timepickerdialog");
    }

    private void setDateDialog() {
        Calendar now = Calendar.getInstance();

        datePickerDialog = DatePickerDialog.newInstance(
                AddElementActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Log.d("TAG", "Dialog was cancelled");
            }
        });
        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }
}
