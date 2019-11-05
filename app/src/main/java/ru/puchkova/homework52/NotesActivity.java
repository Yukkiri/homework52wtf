package ru.puchkova.homework52;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {
    private Button save;
    private EditText note;

    private SharedPreferences myNoteSharedPref;

    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        init();
        getDateFromSharedPref();
    }

    private void init(){
        save = findViewById(R.id.save);
        note = findViewById(R.id.note);

        myNoteSharedPref = getSharedPreferences("MyNote", MODE_PRIVATE);

        save.setOnClickListener(oclSave);
    }

    View.OnClickListener oclSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
            String noteTxt = note.getText().toString();
            myEditor.putString(NOTE_TEXT, noteTxt);
            myEditor.apply();
            Toast.makeText(NotesActivity.this, getString(R.string.success_save), Toast.LENGTH_LONG).show();

        }
    };

    private void getDateFromSharedPref(){
        String noteTxt = myNoteSharedPref.getString(NOTE_TEXT, "");
        note.setText(noteTxt);
    }
}

