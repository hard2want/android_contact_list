package com.example.contactlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
//import java.lang.String;
//import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;

//    private final String[] itemname;
//    private final String[] phone;
//    private final Integer[] imgid;

    private ArrayList<String> itemname = new ArrayList<>();
    private ArrayList<String> phone = new ArrayList<>();
    private ArrayList<Integer> imgid = new ArrayList<>();


    public CustomListAdapter(Activity context, ArrayList itemname, ArrayList phone, ArrayList imgid){
//    public CustomListAdapter(Activity context, ArrayList itemname, ArrayList phone, ArrayList imgid) {
        super(context, R.layout.contactlist, itemname);

        this.context = context;
        this.imgid = itemname;
        this.phone = phone;
        this.imgid = imgid;

    } // end CustomListAdapter()

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.contactlist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.thumbnail);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

//        txtTitle.setText(itemname[position));
//        imageView.setImageResource(imgid[position]);
//        extratxt.setText(phone[position]);

       txtTitle.setText(itemname.get(position));
       imageView.setImageResource(imgid.get(position));
       extratxt.setText(phone.get(position));
        return rowView;
    } // end getView()
    ;
} // end customListAdapter { }
