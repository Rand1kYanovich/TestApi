package com.example.testapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.testapi.api.Get;
import com.example.testapi.api.NetworkService;
import com.example.testapi.util.SharedUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataAdapter extends RecyclerView.Adapter<DataViewHolders>{
    private List<Get.Item> requestList;
    private Context context;
    private OnItemClickListener clickListener;
    private List<Get.Item> filterList;




    public DataAdapter(final List<Get.Item> matchesList, Context context, OnItemClickListener onItemClickListener){
        this.requestList = matchesList;
        this.context = context;
        this.clickListener = onItemClickListener;
        this.filterList = matchesList;


        NetworkService.getInstance()
                .getJSONApi()
                .getList()
                .enqueue(new Callback<Get>() {
                    @Override
                    public void onResponse(Call<Get> call, Response<Get> response) {
                        if(response.isSuccessful()) {
                            requestList = new ArrayList<Get.Item>(response.body().getData());
                            Collections.sort(requestList, new Comparator<Get.Item>() {
                                @Override
                                public int compare(Get.Item o1, Get.Item o2) {
                                    return  (o1.getActualTime()+"").compareTo(o2.getActualTime()+"");
                                }
                            });
                            for (int i = 0;i<requestList.size();i++){
                                if(requestList.get(i).getStatus().equals("open")) {
                                    requestList.get(i).setStatus("Открытые");
                                }else if (requestList.get(i).getStatus().equals("closed")){
                                    requestList.get(i).setStatus("Закрытые");
                                }else if (requestList.get(i).getStatus().equals("in_progress")){
                                    requestList.get(i).setStatus("В процессе");
                                }
                            }
                            setData();
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Get> call, Throwable t) {

                    }
                });
        Log.e("Filter",SharedUtil.getFilter());




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



        Get.Item request = filterList.get(position);
        holder.title.setText(request.getTitle());
        holder.actual_time.setText(request.getActualTime()+"");
        holder.location.setText(request.getLocation());
        holder.status.setText(request.getStatus());
        holder.bind(position,clickListener,request.getId());
    }



    public void setData(){
        String filter = SharedUtil.getFilter();
        filterList.clear();
        if(filter.equals("Все")){
            filterList = new ArrayList<Get.Item>(requestList);
        }
        else {
            for (int i = 0; i < requestList.size(); i++) {
                Get.Item object = requestList.get(i);
                if (object.getStatus().equals(filter)) {
                    Log.e("Equals",object.getStatus());
                    filterList.add(object);

                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return this.filterList.size();
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

    public void bind(final int position, final OnItemClickListener listener,final int id) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v,position,id);
            }
        });
    }




}
