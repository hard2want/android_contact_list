package com.example.contactlist;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editlname, editfname, editphone, editemail;
    SQLiteDatabase db;

    ListView list;

    ArrayList<String> itemname1 = new ArrayList<>();
    ArrayList<String> phone1 = new ArrayList<>();
    ArrayList<Integer> imgid1 = new ArrayList<>();
/*
    String[] itemname = {
            "Contact name",
            "Contact name",
            "Contact name",
            "Contact name",
            "Contact name",
            "Contact name",
            "Contact name",
            "Contact name",
            "Contact name",
    }; // end itemname

    String[] phone = {
            "111-222-3333",
            "111-222-3333",
            "111-222-3333",
            "111-222-3333",
            "111-222-3333",
            "111-222-3333",
            "111-222-3333",
            "111-222-3333",
    }; // end phone

    Integer[] imgid = {
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
            R.drawable.ic_add_contact,
    }; // end imgid
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editlname = (EditText) findViewById(R.id.lname);
        editfname = (EditText) findViewById(R.id.fname);
        editphone = (EditText) findViewById(R.id.phone);
        editemail = (EditText) findViewById(R.id.email);

        db = openOrCreateDatabase("ContactDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ContactTBL(clname VARCHAR(25), cfname VARCHAR(25), cphone VARCHAR(25), cemail VARCHAR(25));");

        ViewAllF();

        CustomListAdapter adapter = new CustomListAdapter(this, itemname1, phone1, imgid1);

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    } // end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    } // end onCreateOptionsMenu()

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } // end if

        return super.onOptionsItemSelected(item);
    } // end onOptionsItemSelected()

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    } // end showMessage()

    public void ViewAll(View v){
        Cursor c = db.rawQuery("select * from ContactTBL;", null);

        if (c.getCount() == 0) {
            showMessage("Error !!!", "No Record Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while (c.moveToNext()) {
            buffer.append("Contact lname : " + c.getString(0) + "\n");
            buffer.append("Contact fname : " + c.getString(1) + "\n");
            buffer.append("Contact phone : " + c.getString(2) + "\n");
            buffer.append("Contact email : " + c.getString(3) + "\n\n");
        } // end while

        showMessage("Contact Details", buffer.toString());
    } // end ViewAll

    public void ViewAllF() {
        int x = 0;
        Cursor c = db.rawQuery("SELECT * FROM ContactTBL;", null);
        if (c.getCount() == 0){
//            itemname[0] = "No Contacts";
//            imgid[0] = R.drawable.ic_add_contact;

            showMessage("No Contacts", "No Record Found");
            return;
        } // end if

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()){

//            itemname[x] = c.getString(1) + " " + c.getString(0);
//            phone[x] = c.getString(2);
//            imgid[x] = R.drawable.ic_add_contact;

            itemname1.add(c.getString(1) + " " + c.getString(0));
            phone1.add(c.getString(2));
            imgid1.add(R.drawable.ic_add_contact);
            x++;
        } // end while
    } // end ViewAllF

} // end MainActivity { }
