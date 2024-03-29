package ru.puchkova.homework52;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HealthActivity extends AppCompatActivity {
    Button save, history, back;
    EditText weight, steps, date;
    TextView historyView, warning;
    String sDate;
    int userId, iSteps;
    float fWeight;

    DBHelper dbHelper;

    Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);


        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", userId);


        save = findViewById(R.id.save);
        history = findViewById(R.id.history);
        back = findViewById(R.id.back);
        weight = findViewById(R.id.weight);
        steps = findViewById(R.id.steps);
        date = findViewById(R.id.date);
        dbHelper = new DBHelper(this);
        historyView = findViewById(R.id.historyView);
        warning = findViewById(R.id.warning);


        setInitialDateTime();



        View.OnClickListener oclSave = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fWeight = Float.parseFloat(weight.getText().toString());
                    iSteps = Integer.parseInt(steps.getText().toString());
                    sDate = date.getText().toString();


                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DBHelper.KEY_ID, userId);
                    contentValues.put(DBHelper.KEY_WEIGHT, fWeight);
                    contentValues.put(DBHelper.KEY_STEPS, iSteps);
                    contentValues.put(DBHelper.KEY_DATE, sDate);

                    database.insert(DBHelper.TABLE_HEALTH, null, contentValues);

                    warning.setVisibility(View.INVISIBLE);
                } catch (NumberFormatException e){
                    warning.setVisibility(View.VISIBLE);
                }

                weight.setText("");
                steps.setText("");
            }
        };




        View.OnClickListener oclHistory = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight.setVisibility(View.INVISIBLE);
                steps.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
                save.setVisibility(View.INVISIBLE);
                history.setVisibility(View.INVISIBLE);
                historyView.setVisibility(View.VISIBLE);
                warning.setVisibility(View.INVISIBLE);
                String text = "\n\n";


                SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = database.rawQuery("SELECT " + DBHelper.KEY_WEIGHT + ", " + DBHelper.KEY_STEPS +  ", " + DBHelper.KEY_DATE +
                        " FROM " + DBHelper.TABLE_HEALTH +
                        " WHERE " + DBHelper.KEY_ID + " = " + userId +  " ORDER BY " + DBHelper.KEY_DATE, null);
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    String currText = text;
                    text = currText + "Дата: " + cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DATE)) +
                            "\n   Вес: " + cursor.getString(cursor.getColumnIndex(DBHelper.KEY_WEIGHT)) +
                            "\n   Шаги: " + cursor.getString(cursor.getColumnIndex(DBHelper.KEY_STEPS)) + "\n\n";
                }

                text = text + "\n\n\n";
                historyView.setText(text);
            }
        };




        View.OnClickListener oclBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };

        save.setOnClickListener(oclSave);
        history.setOnClickListener(oclHistory);
        back.setOnClickListener(oclBack);
    }




    public void setDate(View v) {
        new DatePickerDialog(HealthActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }




    private void setInitialDateTime() {
        SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
        sDate = form.format(dateAndTime.getTimeInMillis());
        date.setText(sDate);
    }




    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}