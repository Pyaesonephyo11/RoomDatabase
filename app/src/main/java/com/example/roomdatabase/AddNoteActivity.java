package com.example.roomdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle,etDes;
    private NumberPicker numberPickerPriority;
    public static final String EXTRA_TITLE="com.example.roomdatabase.EXTRA_TITLE";
    public static final String EXTRA_DES="com.example.roomdatabase.EXTRA_DES";
    public static final String EXTRA_PRIORITY="com.example.roomdatabase.EXTRA_PRIORITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etDes=findViewById(R.id.edit_text_description);
        etTitle=findViewById(R.id.edit_text_title);

        numberPickerPriority=findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.cancel);

        setTitle("Add Note");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
         menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.save_note:saveNote();
                return  true;
            default:  return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {

        String title=etTitle.getText().toString();
        String des=etDes.getText().toString();
        int priority=numberPickerPriority.getValue();

        if (title.trim().isEmpty() || des.trim().isEmpty()){
            Toast.makeText(this,"inset title and description",Toast.LENGTH_LONG).show();
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DES,des);
        data.putExtra(EXTRA_PRIORITY,priority);
        setResult(RESULT_OK,data);

    }
}