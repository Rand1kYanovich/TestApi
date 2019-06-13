package com.example.testapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.api.Get;
import com.example.testapi.api.NetworkService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataAdapter extends RecyclerView.Adapter<DataViewHolders>{
    private List<Get.Item> requestList;
    private Context context;
    private OnItemClickListener clickListener;


    public DataAdapter(final List<Get.Item> matchesList, Context context, OnItemClickListener onItemClickListener){
        this.requestList = matchesList;
        this.context = context;
        this.clickListener = onItemClickListener;

        NetworkService.getInstance()
                .getJSONApi()
                .getList()
                .enqueue(new Callback<Get>() {
                    @Override
                    public void onResponse(Call<Get> call, Response<Get> response) {
                        if(response.isSuccessful()) {
                            requestList = new ArrayList<Get.Item>(response.body().getData());
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Get> call, Throwable t) {

                    }
                });
    }

    @Override
    public DataViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        DataViewHolders rcv = new DataViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(final DataViewHolders holder, int position) {
        final Get.Item request = requestList.get(position);
        holder.title.setText(request.getTitle());

        holder.actual_time.setText(request.getActualTime()+"");
        holder.location.setText(request.getLocation());
        holder.status.setText(request.getStatus());
        holder.bind(position,clickListener);
        Log.e("List",requestList.toString());
        Toast.makeText(context,"Fuck",Toast.LENGTH_SHORT).show();




    }


    @Override
    public int getItemCount() {
        return this.requestList.size();
    }


}

class DataViewHolders extends RecyclerView.ViewHolder  {
    public TextView title,actual_time,location,status;





    public DataViewHolders(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        actual_time = itemView.findViewById(R.id.actual_time);
        location = itemView.findViewById(R.id.location);
        status = itemView.findViewById(R.id.status);



        //mContext = context;
    }

    public void bind(final int position, final OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v,position);
            }
        });
    }
}
