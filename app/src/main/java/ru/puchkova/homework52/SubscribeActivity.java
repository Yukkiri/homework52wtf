package ru.puchkova.homework52;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubscribeActivity extends AppCompatActivity {

    Button okBttn;
    Button clearBttn;
    TextView resultTxt;
    EditText nameETxt;
    EditText mailETxt;
    String name;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        okBttn = findViewById(R.id.okBttn);
        clearBttn = findViewById(R.id.clearBttn);
        resultTxt = findViewById(R.id.resultTxt);
        nameETxt = findViewById(R.id.nameETxt);
        mailETxt = findViewById(R.id.mailETxt);

        View.OnClickListener oclOkBttn = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                name = nameETxt.getText().toString();
                mail = mailETxt.getText().toString();
                if(name.equals("") && mail.equals("")) {
                    resultTxt.setText(getString(R.string.invalid));
                } else if (name.equals("") && !mail.equals("")){
                    resultTxt.setText(getString(R.string.invalidName));
                } else if (!name.equals("") && mail.equals("")){
                    resultTxt.setText(getString(R.string.invalidMail));
                } else {
                    resultTxt.setText(getString(R.string.correct) + " " + name + " " + getString(R.string.correctMail) + " " + mail + ".");
                }
            }
        };

        View.OnClickListener oclClearBttn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameETxt.setText("");
                mailETxt.setText("");
            }
        };

        okBttn.setOnClickListener(oclOkBttn);
        clearBttn.setOnClickListener(oclClearBttn);

    }
}
