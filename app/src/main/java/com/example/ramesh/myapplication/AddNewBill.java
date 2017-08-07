package com.example.ramesh.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ramesh.myapplication.database.DataBaseHelper;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.DataBaseHelper1;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.DataModel;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class AddNewBill extends AppCompatActivity  implements View.OnClickListener{


    //classes referances
    Context context;
    DataBaseHelper dataBaseHelper;
    DataBaseHelper1 dataBaseHelper1;
    SQLiteDatabase db;
    Model model;

    //ui/ux referances
    TableLayout tv , tb;
    Button add,edit,generateBill ,delete ,ok;
    AutoCompleteTextView ed ,ed1;
    TextView tv2 , date , bill_no,tv3 ,tv4 ,total ,tv5,tv6,tv7, tamount;
    TableRow tr , tr1;



   // variables referances
    double val1;
    int billNumber ,qnty;
    String name;
    double price, amount ,grandTotal;
    static String[] ITEMS;



  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);

        //ui/ux inti
        add=(Button)findViewById(R.id.additem);
        total=(TextView)findViewById(R.id.totalamount);
        tv2=new TextView(this);
        tv4=new TextView(this);
        tr=new TableRow(this);
        tamount = (TextView) findViewById(R.id.textView10);
        generateBill=(Button)findViewById(R.id.genbill);
        date=(TextView)findViewById(R.id.date);
        bill_no=(TextView)findViewById(R.id.bill_no);
        tv=(TableLayout)findViewById(R.id.tablelayout);
        ok = (Button) findViewById(R.id.ok);


        //var intia
        ITEMS=new String[]{ "shirt1" ,"shirt2" ,"shirt3" ,"shirt4" ,"shirt5" ,"pant1" ,"pant2" ,"pant3" ,"pant4" ,"pant5"};
        String dt=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        date.setText(dt);
        int min = 65;
        int max = 80;
        Random r = new Random();
        billNumber = r.nextInt(max - min + 1) + min;
        bill_no.setText(String.valueOf(billNumber));
        qnty = 0;


        //ui/ux listner
        add.setOnClickListener(this);
        tamount.setOnClickListener(this);
        generateBill.setOnClickListener(this);
        tr.setVisibility(View.GONE);
        ok.setOnClickListener(this);

        //classes inti
        model=new Model();
        dataBaseHelper=new DataBaseHelper(this);
        dataBaseHelper1=new DataBaseHelper1(this);


    }

//    private void showDialog()
//    {
//        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle("Are You Sure To Delete This Data")
//                                           .setMessage("Delete")
//                                            .setPositiveButton("Ok" , new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//                                                    Activity.setDialogResult(true);
//                                                }
//                                            });
//        AlertDialog dialog=builder.create();
//        dialog.show();
//    }
 //listners
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            View row=(View)v.getParent();
            ViewGroup vg=(ViewGroup)row.getParent();

            vg.removeView(row);
            vg.invalidate();
            long id = row.getId();
            dataBaseHelper1.deleteDataTemp(new Model(id));


        }
    };

    private View.OnClickListener corkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            tv5.setCursorVisible(true);
            tv5.setFocusableInTouchMode(true);
            tv5.setInputType(InputType.TYPE_CLASS_NUMBER);
            tv5.requestFocus();
            tv5.setFocusable(true);
            tv5.setEnabled(true);
            tv5.setClickable(true);
            tv5.setCursorVisible(true);
            tv5.setFocusableInTouchMode(true);
            View row = (View) v.getParent();
            ViewGroup vg = (ViewGroup) row.getParent();
            long id = row.getId();
            dataBaseHelper1.updateDataTemp(new Model(id));

        }
    };


    @Override
    public void onClick(View view) {

        if (view == add) {

            tr1 = new TableRow(this);
            tr1.setClickable(true);
            tr1.setOnClickListener(this);
            tv5 = new TextView(this);
            tv6 = new TextView(this);
            tv7 = new TextView(this);
            ed1 = new AutoCompleteTextView(this);
            delete=new Button(this);
            edit=new Button(this);
            tv5.setText(String.valueOf(qnty));
            layoutSet1();
            delete.setOnClickListener(mCorkyListener);
            edit.setOnClickListener(corkyListener);



            }




        else if (view == generateBill) {


            db = dataBaseHelper1.getWritableDatabase();
            int it = dataBaseHelper1.DATA_BASE_VAERSION + 1;
            dataBaseHelper1.onUpgrade(db, dataBaseHelper1.DATA_BASE_VAERSION, it);

            bill_no.setText(String.valueOf(billNumber));
            int billno = Integer.parseInt(bill_no.getText().toString());
            String dt = date.getText().toString();
            double am = Double.parseDouble(total.getText().toString());
            Log.e("data" ,"DATA INSERTING");
            dataBaseHelper.saveData(new DataModel(billno,dt,am));
            Log.e("D","Sucess");
            dataBaseHelper.getAllData();
            Log.e("Dr","Data Get......");
            Intent i = new Intent(AddNewBill.this, MainActivity.class);
            startActivity(i);
        }



        else if (view == tamount)
        {
            double i = dataBaseHelper1.sumDataTemp();
            total.setText(String.valueOf(i));
        }

        else if (view == ok) {
            Model d = new Model(name, qnty, price, amount);
            long tablerowid;
            tablerowid = dataBaseHelper1.saveDataTemp(d);
            d.setId(tablerowid);

            tr1.setId((int) d.getId());
        }
    }




//

    public void layoutSet1()
    {

        TableLayout.LayoutParams params=new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,0,0,0);

        TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5,10,5,0);

        ed1=new AutoCompleteTextView(this);
        ed1.setWidth(130);

      // array adapter

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,ITEMS);
        ed1.setAdapter(adapter);
        ed1.setThreshold(2);
        tr1.addView(ed1,layoutParams);
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("shirt1"))
                {
                    tv6.setText(String.valueOf(200.99));

                }
                else if (editable.toString().equals("shirt2"))
                {
                    tv6.setText(String.valueOf(300));
                }

                else if (editable.toString().equals("shirt3"))
                {
                    tv6.setText(String.valueOf(400));
                }
                else if (editable.toString().equals("shirt4"))
                {
                    tv6.setText(String.valueOf(500));
                }
                else if (editable.toString().equals("shirt5"))
                {
                    tv6.setText(String.valueOf(600));
                }
                else if (editable.toString().equals("pant1"))
                {
                    tv6.setText(String.valueOf(700));
                }
                else if (editable.toString().equals("pant2"))
                {
                    tv6.setText(String.valueOf(800));
                }
                else if (editable.toString().equals("pant3"))
                {
                    tv6.setText(String.valueOf(999.99));
                }
                else if (editable.toString().equals("pant4"))
                {
                    tv6.setText(String.valueOf(150));
                }
                else
                {
                    tv6.setText(String.valueOf(250));
                }

                price=Double.parseDouble(tv6.getText().toString() );
            }
        });

        tv5.addTextChangedListener(new TextWatcher() {
            String x;
            @Override

            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                tv5.setText(String.valueOf(qnty));
                x=(charSequence.toString());

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                tv5.removeTextChangedListener(this);
                if (tv5.isInEditMode())
                {
                    tv5.setText(( x + " "));
                }

                tv5.addTextChangedListener(this);

                qnty =Integer.parseInt(tv5.getText().toString());

                double va=price*qnty;
                tv7.setText(String.valueOf(va));
                amount=(Double.parseDouble(tv7.getText().toString()));

            }
        });

        tv5.setWidth(40);
        tv5.setText(String.valueOf(1));
        tr1.addView(tv5,layoutParams);
        ed1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tv6=new TextView(this);

        tv6.setWidth(70);

        tr1.addView(tv6,layoutParams);
        tv7.setWidth(70);
        name = ed1.getText().toString();

        tr1.addView(tv7,layoutParams);
        edit=new Button(this);
        edit.setText("EDIT");
        tr1.addView(edit,layoutParams);

       delete=new Button(this);
        delete.setText("DELETE");
        tr1.addView(delete,layoutParams);

        tv.addView(tr1,params);
    }
}



