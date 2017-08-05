package com.example.ramesh.myapplication;

import android.content.Context;
import android.content.Intent;

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
import com.example.ramesh.myapplication.database.com.example.ramesh.model.DataModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class AddNewBill extends AppCompatActivity  implements View.OnClickListener{

    Context context;
    DataBaseHelper dataBaseHelper;
    TableLayout tv , tb;
    Button add,edit,generateBill ,delete;




    double val1,val,pr1,pr2;
    int billNumber;

    AutoCompleteTextView ed ,ed1;


    TextView tv2 , date , bill_no,tv3 ,tv4 ,total ,tv5,tv6,tv7;




    static String[] ITEMS;

    double grandTotal ,grandTotal1;

TableRow tr , tr1;
  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);

        add=(Button)findViewById(R.id.additem);

        total=(TextView)findViewById(R.id.totalamount);
        tv2=new TextView(this);
         tv4=new TextView(this);
        tr=new TableRow(this);
        tr.setClickable(true);

        tr.setOnClickListener(this);
     ITEMS=new String[]{ "shirt1" ,"shirt2" ,"shirt3" ,"shirt4" ,"shirt5" ,"pant1" ,"pant2" ,"pant3" ,"pant4" ,"pant5"};
        add.setOnClickListener(this);
        //edit=(Button)findViewById(R.id.edit);
//        edit.setOnClickListener(this);
        dataBaseHelper=new DataBaseHelper(this);
        generateBill=(Button)findViewById(R.id.genbill);
        generateBill.setOnClickListener(this);
        date=(TextView)findViewById(R.id.date);
        String dt=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        date.setText(dt);
     bill_no=(TextView)findViewById(R.id.bill_no);


        tv=(TableLayout)findViewById(R.id.tablelayout);
        //delete=(Button)findViewById(R.id.delete);
        //delete.setOnClickListener(this);


        int min = 65;
        int max = 80;

        Random r = new Random();
      billNumber = r.nextInt(max - min + 1) + min;
        bill_no.setText(String.valueOf(billNumber));
        tr.setVisibility(View.GONE);

//        dataModels=dataBaseHelper.getAllData();
     layoutSet();

    }

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
            layoutSet1();
            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
            if (view==edit)
            {
                tv2.setCursorVisible(true);
                tv2.setFocusableInTouchMode(true);
                tv2.setInputType(InputType.TYPE_CLASS_NUMBER);
                tv2.requestFocus();
                tv2.setFocusable(true);
                tv2.setEnabled(true);
                tv2.setClickable(true);

                tv2.setCursorVisible(true);
                tv2.setFocusableInTouchMode(true);


                tv2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        tv2.setText(String.valueOf(1));
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        tv2.setText("");

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


            }
            else if (view==delete)
            {
                if (view == tr1) {
                    view.setBackgroundColor(Color.GRAY);
                    tr1.removeAllViews();
                }
            }

        }


        else if (view == generateBill) {

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

            //i.putExtra("nu" ,billNumber);
            startActivity(i);
        }
    }




    public void layoutSet()
    {

        TableLayout.LayoutParams params=new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(40,0,0,0);

        TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30,10,5,0);

       ed=new AutoCompleteTextView(this);
        ed.setWidth(150);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,ITEMS);
        ed.setAdapter(adapter);
      ed.setThreshold(2);


        tr.addView(ed,layoutParams);

//


        ed.addTextChangedListener(new TextWatcher() {
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
                    tv3.setText(String.valueOf(200.99));

                }
                else if (editable.toString().equals("shirt2"))
                {
                    tv3.setText(String.valueOf(300));
                }

                else if (editable.toString().equals("shirt3"))
                {
                    tv3.setText(String.valueOf(400));
                }
                else if (editable.toString().equals("shirt4"))
                {
                    tv3.setText(String.valueOf(500));
                }
                else if (editable.toString().equals("shirt5"))
                {
                    tv3.setText(String.valueOf(600));
                }
                else if (editable.toString().equals("pant1"))
                {
                    tv3.setText(String.valueOf(700));
                }
                else if (editable.toString().equals("pant2"))
                {
                    tv3.setText(String.valueOf(800));
                }
                else if (editable.toString().equals("pant3"))
                {
                    tv3.setText(String.valueOf(999.99));
                }
                else if (editable.toString().equals("pant4"))
                {
                    tv3.setText(String.valueOf(150));
                }
                else
                {
                    tv3.setText(String.valueOf(250));
                }



                 double p=Double.parseDouble(
                        tv3.getText().toString() );
                int qn=Integer.parseInt(tv2.getText().toString());
                double va=p*qn;
                tv4.setText(String.valueOf(va));
                 val1=va;


                 double grandTotl1=0;
                grandTotl1=grandTotl1+val1;
                grandTotal=grandTotl1;
                total.setText(String.valueOf(grandTotl1));



            }
        });






        tv2.setWidth(70);
        tv2.setText(String.valueOf(1));

        tr.addView(tv2,layoutParams);






        ed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tv3=new TextView(this);

        tv3.setWidth(70);

        tr.addView(tv3,layoutParams);





        tv4.setWidth(70);

        tr.addView(tv4,layoutParams);
        tv.addView(tr,params);
    }


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




              double  p2=Double.parseDouble(tv6.getText().toString() );
               int  qnt1=Integer.parseInt(tv5.getText().toString());



                double va=p2*qnt1;
                tv7.setText(String.valueOf(va));
                val=va;






            }
        });


        grandTotal1= grandTotal1+grandTotal+val;
        total.setText(String.valueOf(grandTotal1));





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



