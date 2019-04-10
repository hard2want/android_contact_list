package com.example.contactlist;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.database.Cursor;

public class InsertActivity extends AppCompatActivity {

    EditText editlname, editfname, editphone, editemail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editlname = (EditText) findViewById(R.id.lname);
        editfname = (EditText) findViewById(R.id.fname);
        editphone = (EditText) findViewById(R.id.phone);
        editemail = (EditText) findViewById(R.id.email);

        db = openOrCreateDatabase("ContactDB", MODE_PRIVATE, null);
        db.execSQL("create table if not exists ContactTBL(clname varchar(25), cfname varchar(25), cphone varchar(25), cemail varchar(25));");
    } // end onCreate()

    public void showMessage(String title, String message){
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    } // end showMessage()

    public void clearText(){
        editlname.setText("");
        editfname.setText("");
        editphone.setText("");
        editemail.setText("");
        editlname.requestFocus();
    } // end clearText()

    public void save(View v){
        if (editlname.getText().toString().trim().length() == 0 || editfname.getText().toString().trim().length() == 0 || editphone.getText().toString().trim().length() == 0 ||editemail.getText().toString().trim().length() == 0){
            showMessage("Error", "Please enter all values");
            return;
        } // end if

        db.execSQL("insert into ContactTBL values('" + editlname.getText() + "', '" + editfname.getText() + "', '" + editphone.getText() + "', '" + editemail.getText() + "');");
        showMessage("Success", "You added a new contact");
        clearText();
        startActivity(new Intent(InsertActivity.this, MainActivity.class));
    } // end save()

    public void cancel(View v){
        clearText();
        editlname.requestFocus();
    } // end cancel()

} // end InsertActivity {}
