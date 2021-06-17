package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {
  TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        tvData=findViewById(R.id.tvData);
        ContactsDB contactsDB=new ContactsDB(this);
        contactsDB.open();
        contactsDB.close();
        tvData.setText(contactsDB.getData());
        contactsDB.close();
    }
}