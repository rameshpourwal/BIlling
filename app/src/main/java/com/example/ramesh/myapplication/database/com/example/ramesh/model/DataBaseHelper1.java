package com.example.ramesh.myapplication.database.com.example.ramesh.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter.Model;

/**
 * Created by prem on 7/8/17.
 */

public class DataBaseHelper1 extends SQLiteOpenHelper {
    public static final  int DATA_BASE_VAERSION=1;
    public static final String DATA_BASE_NAME="temp1";
    public static final String  DEMO_TABLE="temp";
    public static  final  String ID="id";
    public static final String NAME="name";
    public static final String QNTY="qnty";
    public static final  String PRICE="price";
    public static final String AMOUNT="amount";

    public DataBaseHelper1(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VAERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_DEMO= " CREATE TABLE " + DEMO_TABLE + " ( "  + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                NAME + " TEXT , " + QNTY + " INTEGER , " + PRICE + " REAL , " + AMOUNT + " REAL " + " ) ;" ;

        sqLiteDatabase.execSQL(CREATE_TABLE_DEMO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query= "  DROP TABLE IF EXISTS " + DEMO_TABLE ;
        sqLiteDatabase.execSQL( query );
        onCreate(sqLiteDatabase);


    }

    public long saveDataTemp( Model dataModel)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        // values.put(ID,dataModel.getId());
        values.put(NAME,dataModel.getName());
        values.put(QNTY,dataModel.getQnty());
        values.put(PRICE,dataModel.getPrice());
        values.put(AMOUNT,dataModel.getAmount());
        long newrowid=db.insert(DEMO_TABLE,null,values);
        db.close();
        return  newrowid;


    }
    public void updateDataTemp(Model model)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(QNTY,model.getQnty());
        values.put(AMOUNT,model.getAmount());
        long id=model.getId();
        db.update(DEMO_TABLE,values,ID + " = ?" , new String[]{Long.toString(id)});
        db.close();

    }

    public  void deleteDataTemp(Model dataModel)
    {
//        DataModel model=new DataModel();
        SQLiteDatabase db=this.getWritableDatabase();
//        String query="DELETE FROM " + DEMO_TABLE + "WHERE " + ID + " = ?";
//        db.execSQL(query);
        long id=dataModel.getId();
        db.delete(DEMO_TABLE,ID + " = ? " ,new String[] {Long.toString(id)});
        db.close();
    }
    public double  sumDataTemp()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String query =" SELECT SUM ( " + AMOUNT + " ) " + " FROM " + DEMO_TABLE ;

        Cursor c=db.rawQuery(query ,null);
        double sum=0;
        if (c.moveToFirst())
        {
            sum=c.getDouble(0);
        }
        return  sum;
    }

}
