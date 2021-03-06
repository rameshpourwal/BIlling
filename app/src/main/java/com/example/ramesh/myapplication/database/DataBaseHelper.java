package com.example.ramesh.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.ramesh.myapplication.database.com.example.ramesh.model.DataModel;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prem on 3/8/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final  int DATA_BASE_VAERSION=2;
    public static final String DATA_BASE_NAME="demo";
    public static final String  DATA_TABLE="data";
    public static  final  String P_ID="id";
    public static final String INVOICENO="invoiceNo";
    public static final String DATE="date";
    public static final String TOTAL_AMOUNT="total_amount";


    public DataBaseHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VAERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



        final String CREATE_TABLE_DATA= " CREATE TABLE " + DATA_TABLE + " ( "  + 
                                         INVOICENO + " INTEGER  , " + DATE + " TEXT , " + TOTAL_AMOUNT + " REAL " + " ) ;" ;

        sqLiteDatabase.execSQL(CREATE_TABLE_DATA);





    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



        public void saveData( DataModel dataModel)
        {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values=new ContentValues();

           // values.put(ID,dataModel.getId());
            values.put(INVOICENO,dataModel.getBillNo());
            values.put(DATE,dataModel.getDate());
            values.put(TOTAL_AMOUNT,dataModel.getPrice());
            db.insert(DATA_TABLE,null,values);
            db.close();


        }
    public List<DataModel> getAllData()
    {
        List<DataModel> items=new ArrayList<>();
        String query="SELECT * FROM " + DATA_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do
            {
                DataModel model=new DataModel();
               // model.setId(Integer.parseInt(cursor.getString(0)));
                model.setBillNo(Integer.parseInt(cursor.getString(0)));
                model.setDate(cursor.getString(1));
                model.setPrice(Double.parseDouble(cursor.getString(2)));
                //model.setPrice(Double.parseDouble(cursor.getString(2)));
                items.add(model);
            } while (cursor.moveToNext());

        }
        return  items;
    }

}


