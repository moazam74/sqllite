package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.security.keystore.KeyNotYetValidException;

public class ContactsDB {
    public static final String  KEY_ROWID="_id";
    public static final String KEY_CELL="_cell";
    public static final String KEY_Name="_name";

    private final String DATABASE_NAME="ContactsDB";
    private final String DATABASE_TABLE="ContactsTables";
    private final int DATABASE_VERSION=1;
    private DBHelper ourHelper;
    private final Context ourContacts;
    private SQLiteDatabase ourDataBase;
    ContactsDB(Context context)
    {
        ourContacts=context;
    }
    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context)
        {
super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode="Create Table "+DATABASE_TABLE+"( "
                    + KEY_ROWID +"INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_Name+"TEXT NOT NULL,"
                    +KEY_CELL+"TEXT NOT NULL);";

        db.execSQL(sqlCode);}

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
          onCreate(db);
        }
    }
    public ContactsDB open()
    {
        ourHelper=new DBHelper(ourContacts);
        ourDataBase= ourHelper.getWritableDatabase();
        return  this;
    }
    public void close()
    {
        ourHelper.close();
    }
    public long createEntery(String name,String cell)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_Name,name);
        cv.put(KEY_CELL,cell);
        return ourDataBase.insert(DATABASE_TABLE,null,cv);
    }
    public String getData()
    {
        String []colums= new String[]{KEY_ROWID,KEY_Name,KEY_CELL};
        Cursor c=ourDataBase.query(DATABASE_TABLE,colums,null,null,null,null,null);
       String results = "";
       int rowId=c.getColumnIndex(KEY_ROWID);
       int rowname=c.getColumnIndex(KEY_Name);
       int rowCell=c.getColumnIndex(KEY_CELL);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            results=results+c.getString(rowId)+" "+ c.getString(rowname)+" "+c.getString(rowCell)+"\n";
        }
        c.close();
        return results;
    }
    public long updateEntery(String rowId,String newName,String newCell)
    {
        ContentValues cv=new ContentValues();
        cv.put(KEY_Name,newName);
        cv.put(KEY_CELL,newCell);

        return ourDataBase.update(DATABASE_TABLE,cv,KEY_ROWID+"=?",new String[]{rowId});
    }
    public long deleteEntery(String rowId)
        {
            return ourDataBase.delete(DATABASE_TABLE,KEY_ROWID+"?",new String[]{rowId});
        }
}
