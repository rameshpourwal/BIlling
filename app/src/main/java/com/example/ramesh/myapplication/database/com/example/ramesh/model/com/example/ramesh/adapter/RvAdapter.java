package com.example.ramesh.myapplication.database.com.example.ramesh.model.com.example.ramesh.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramesh.myapplication.R;
import com.example.ramesh.myapplication.database.com.example.ramesh.model.DataModel;

import java.util.List;

/**
 * Created by prem on 5/8/17.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private List<DataModel> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bill_no,date,amount;

        public MyViewHolder(View view) {
            super(view);
            bill_no = (TextView) view.findViewById(R.id.billnumber1);
            date= (TextView) view.findViewById(R.id.date1);
            amount = (TextView) view.findViewById(R.id.amount1);
        }
    }


    public RvAdapter(List<DataModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataModel movie = moviesList.get(position);
        holder.bill_no.setText(String.valueOf(movie.getBillNo()));
        holder.date.setText(movie.getDate());
        holder.amount.setText(String.valueOf(movie.getPrice()));
       //holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}