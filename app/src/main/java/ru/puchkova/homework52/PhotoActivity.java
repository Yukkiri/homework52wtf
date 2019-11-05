package ru.puchkova.homework52;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PhotoActivity extends AppCompatActivity {

    TextView photo;
    Button forward;
    Button back;
    private Object MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forward = (Button) findViewById(R.id.forward);
        back = (Button) findViewById(R.id.back);
        photo = (TextView) findViewById(R.id.photo);

        Random random = new Random();
        int photoNumber = random.nextInt(100);
        String address = getString(R.string.photo_num) + photoNumber;
        photo.setText(address);


        View.OnClickListener oclBackBttn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };

        View.OnClickListener oclForwardBttn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PhotoActivity.class);
                startActivity(intent);
            }
        };

        back.setOnClickListener(oclBackBttn);
        forward.setOnClickListener(oclForwardBttn);
    }


}