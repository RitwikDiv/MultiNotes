package com.ritwikdivakaruni.multinotes;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class EditActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editContent;
    private note currentNote;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTitle =  findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        currentNote = new note(null, null, null);

        Intent intent = getIntent();
        if(intent.hasExtra("CURRENT_NOTE")) {
            currentNote = (note) intent.getSerializableExtra("CURRENT_NOTE");
            editTitle.setText(currentNote.getTitle());
            editContent.setText(currentNote.getContent());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.saveButton:
                if(editTitle.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Can't save un-titled note", Toast.LENGTH_SHORT).show();

                    Intent data = new Intent();
                    setResult(RESULT_CANCELED, data);
                    finish();
                }
                else if(editTitle.getText().toString().equals(currentNote.getTitle()) &&
                        editContent.getText().toString().equals(currentNote.getContent())) {
                    Intent data = new Intent();
                    setResult(RESULT_CANCELED, data);
                    finish();
                }
                else {
                    note updatedNote = new note(editTitle.getText().toString(),
                            editContent.getText().toString(), getCurrentTime());
                    Intent data = new Intent();
                    data.putExtra((currentNote.getTitle() == null?"NEW_NOTE":"UPDATED_NOTE"), updatedNote);
                    setResult(RESULT_OK, data);

                    finish();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        if(editTitle.getText().toString().trim().isEmpty() ||
                (editTitle.getText().toString().equals(currentNote.getTitle()) &&
                        editContent.getText().toString().equals(currentNote.getContent()))) {
            Intent data = new Intent();
            setResult(RESULT_CANCELED, data);
            finish();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save note \'" + editTitle.getText().toString() + "\' ?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    note updatedNote = new note(editTitle.getText().toString(),
                            editContent.getText().toString(), getCurrentTime());
                    Intent data = new Intent();
                    data.putExtra((currentNote.getTitle() == null?"NEW_NOTE":"UPDATED_NOTE"), updatedNote);
                    setResult(RESULT_OK, data);

                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent data = new Intent();
                    setResult(RESULT_CANCELED, data);
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("E MMM d, h:mm a");
        return df.format(new Date()).toString();
    }
}