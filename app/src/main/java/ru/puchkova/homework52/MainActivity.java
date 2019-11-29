package ru.puchkova.homework52;



import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_open_notes:
                Toast.makeText(MainActivity.this, getString(R.string.open_notes), Toast.LENGTH_LONG).show();
                Intent intentNotes = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intentNotes);
                return true;
            case R.id.action_open_address:
                Toast.makeText(MainActivity.this, getString(R.string.open_address), Toast.LENGTH_LONG).show();
                Intent intentAddress = new Intent(MainActivity.this, AddressActivity.class);
                startActivity(intentAddress);
                return true;
            case R.id.action_open_health:
                Toast.makeText(MainActivity.this, getString(R.string.open_health), Toast.LENGTH_LONG).show();
                Intent intentHealth = new Intent(MainActivity.this, MonitorActivity.class);
                startActivity(intentHealth);
                return true;
            case R.id.action_open_money:
                Toast.makeText(MainActivity.this, getString(R.string.open_money), Toast.LENGTH_LONG).show();
                Intent intentMoney = new Intent(MainActivity.this, MoneyActivity.class);
                startActivity(intentMoney);
                return true;
            case R.id.action_open_photo:
                Toast.makeText(MainActivity.this, getString(R.string.open_photo), Toast.LENGTH_LONG).show();
                Intent intentPhoto = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intentPhoto);
                return true;
            case R.id.action_open_subscribe:
                Toast.makeText(MainActivity.this, getString(R.string.open_subscribe), Toast.LENGTH_LONG).show();
                Intent intentSubscribe = new Intent(MainActivity.this, SubscribeActivity.class);
                startActivity(intentSubscribe);
                return true;
            case R.id.action_open_tasks:
                Toast.makeText(MainActivity.this, getString(R.string.open_tasks), Toast.LENGTH_LONG).show();
                Intent intentTasks = new Intent(MainActivity.this, TasksActivity.class);
                startActivity(intentTasks);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
