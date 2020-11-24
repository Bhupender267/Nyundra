package com.example.hpseb.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name,village, amount;
    String sname,svillage,samount;
    TextView textview;
    DBHelper db;
    public ArrayList<String> get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.editText);
        village=findViewById(R.id.editText2);
        amount=findViewById(R.id.editText3);

        db=new DBHelper(this);
        //get.set(1,"Two");
        //get.set(2,"Three");

    }

    public void clear (View view)
    {
        name.setText("");
        village.setText("");
        amount.setText("");
    }
    public void delete(View view)
    {
        String s;
        s=name.getText().toString();
        try
        {

        db.delete(s);
        Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Could not Delete"+s, Toast.LENGTH_SHORT).show();
        }
    }

    public void show(View view)
               {
                   String s;

                 try
                 {
                     get=db.getAllCotacts();
                     s=name.getText().toString();

                     for(int i=0;i<40;i++) {
                         Toast.makeText(getApplicationContext(), "Looping"+get.get(i).toString(), Toast.LENGTH_SHORT).show();

                            name.append(get.get(i).toString());



                 }
                     Toast.makeText(getApplicationContext(), "Show reached", Toast.LENGTH_SHORT).show();
                 }
                 catch(Exception e)
                   {
                       Toast.makeText(getApplicationContext(), "Exception in  show", Toast.LENGTH_SHORT).show();
                   }

    }
    public void insert(View view) {


        sname = name.getText().toString();
        svillage = village.getText().toString();
        samount = amount.getText().toString();
        try {
            if (db.insertContact(sname, svillage, samount) == true) {
                Toast.makeText(getApplicationContext(), "Sucessful update", Toast.LENGTH_SHORT).show();
                name.setText("");
                village.setText("");
                amount.setText("");

            }
            else{
                Toast.makeText(getApplicationContext(), "DB handler error", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }
    }
}
