package com.example.siddhant.firebassign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{
    List<AssnModel> list;
    List<String> alist;
    Context ctx;
    MainActivity pol;

    public RecyclerAdapter(List<AssnModel> list, Context ctx){
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item,parent,false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        AssnModel data=list.get(position);
        holder.age.setText(data.getUserAge());
        holder.name.setText(data.getUserName());

     }
    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }
        }catch (Exception e){

        }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,age;


        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.list_name);
            age= (TextView) itemView.findViewById(R.id.list_age);

        }
    }

}
