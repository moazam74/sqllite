package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText etName,etCell;
    ContactsDB contactsDB;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intit();
    }

    private void intit() {
        etName=findViewById(R.id.etName);
        etCell=findViewById(R.id.etCellNumber);
         contactsDB=new ContactsDB(this);
}
    public void clear()
    {
        etName.setText("");
        etCell.setText("");
    }

    public void btnsubmit(View v)

    {
      String name=etName.getText().toString().trim();
      String cell=etCell.getText().toString().trim();
      contactsDB=new ContactsDB(this);
      contactsDB.open();
      contactsDB.close();
      contactsDB.createEntery(name,cell);

    }
    public void btnshowData(View v)

    {
       startActivity(new Intent(MainActivity.this,ShowData.class));
    }

    public void btnEditData(View v)
    {
      contactsDB.open();
    long totalUpdateRecords=contactsDB.updateEntery("1","Rana waqas ali","02334734");
        Toast.makeText(this,""+totalUpdateRecords,Toast.LENGTH_SHORT).show();
    contactsDB.close();
    }

    public void btnDeleteData(View v) {
    contactsDB.open();
    contactsDB.deleteEntery("1");
    contactsDB.close();

    }}