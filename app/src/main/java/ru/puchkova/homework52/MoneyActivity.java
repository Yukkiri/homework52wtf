package ru.puchkova.homework52;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MoneyActivity extends AppCompatActivity {

    private TextView info;
    private CheckBox card;
    private CheckBox phone;
    private CheckBox cash;
    private Button ok;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        initViews();
    }

    private void initViews(){
        info = findViewById(R.id.info);
        card = findViewById(R.id.card);
        phone = findViewById(R.id.phone);
        cash = findViewById(R.id.cash);
        ok = findViewById(R.id.ok);

        card.setOnCheckedChangeListener(checked);
        phone.setOnCheckedChangeListener(checked);
        cash.setOnCheckedChangeListener(checked);
        ok.setOnClickListener(onClickListenerOk);
        resetChecks();
    }

    private void resetChecks(){
        card.setChecked(false);
        phone.setChecked(false);
        cash.setChecked(false);
    }

    private CompoundButton.OnCheckedChangeListener checked = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                info.setText("");
                switch (buttonView.getId()){
                    case R.id.card:
                        resetChecks();
                        card.setChecked(true);
                        info.setInputType(InputType.TYPE_CLASS_NUMBER);
                        text = getString(R.string.cardPay);
                        break;
                    case R.id.phone:
                        resetChecks();
                        phone.setChecked(true);
                        info.setInputType(InputType.TYPE_CLASS_PHONE);
                        text = getString(R.string.phonePay);
                        break;
                    case R.id.cash:
                        resetChecks();
                        cash.setChecked(true);
                        info.setInputType(InputType.TYPE_CLASS_TEXT);
                        text = getString(R.string.cashPay);
                        break;
                }
            }
        }
    };

    private View.OnClickListener onClickListenerOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MoneyActivity.this, text, Toast.LENGTH_LONG).show();
        }
    };
}

