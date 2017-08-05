package com.example.ramesh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.ramesh.myapplication.database.DataBaseHelper;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.DataModel;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter.RvAdapter;
//import com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter.RvAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button new_bill;
    DataBaseHelper dataBaseHelper;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<String> arrayList;
    RvAdapter rvAdapter;
    List <DataModel> dataModels;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        dataBaseHelper=new DataBaseHelper(this);
        dataModels=dataBaseHelper.getAllData();
        rvAdapter=new RvAdapter(dataModels);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rvAdapter);

//        // The number of Columns
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

       //adapter = new GridAdapter(MainActivity.this, arrayList);


//

//        recyclerView.setAdapter(rvAdapter);




//        dataBaseHelper.saveItem(new ItemModel("shirt1" ,200.99));
//        dataBaseHelper.saveItem(new ItemModel("shirt2", 300));
//        dataBaseHelper.saveItem(new ItemModel("shirt3", 400));
//        dataBaseHelper.saveItem(new ItemModel("shirt4", 500));
//        dataBaseHelper.saveItem(new ItemModel("shirt5", 600));
//        dataBaseHelper.saveItem(new ItemModel("pant1", 700));
//        dataBaseHelper.saveItem(new ItemModel("pant2", 800));
//        dataBaseHelper.saveItem(new ItemModel("pant3", 999.99));
//        dataBaseHelper.saveItem(new ItemModel("pant4", 150));
//        dataBaseHelper.saveItem(new ItemModel("pant5", 250));
        new_bill=(Button)findViewById(R.id.newbill);




        new_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i=new Intent(MainActivity.this,AddNewBill.class);

                startActivity(i);
                rvAdapter.notifyDataSetChanged();
            }
        });
    }
}
