package com.example.testapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testapi.api.GetTasksResponse;
import com.example.testapi.api.NetworkService;
import com.example.testapi.util.SharedUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataAdapter extends RecyclerView.Adapter<DataViewHolders>{
    private List<GetTasksResponse.Item> requestList;
    private Context context;
    private OnItemClickListener clickListener;
    private List<GetTasksResponse.Item> filterList;




    public DataAdapter( Context context, OnItemClickListener onItemClickListener){
        this.context = context;
        this.clickListener = onItemClickListener;
        this.filterList = new ArrayList<>();
        this.requestList = new ArrayList<>();




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
        GetTasksResponse.Item request = filterList.get(position);
        Log.e("MM",filterList.get(position)+"");
        holder.title.setText(request.getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        long second = Long.valueOf(request.getActualTime())*1000;
        String time = simpleDateFormat.format(second);
        holder.actual_time.setText(time);
        holder.location.setText(request.getLocation());
        holder.status.setText(request.getStatus());
        holder.bind(position,clickListener,request.getId());
    }



    public void setData(){
        String filter = SharedUtil.getFilter();
        if(filterList!=null)filterList.clear();

        if(filter.equals("Все")){
            filterList = new ArrayList<GetTasksResponse.Item>(requestList);

        }
        else {
            for (int i = 0; i < requestList.size(); i++) {
                GetTasksResponse.Item object = requestList.get(i);
                if (object.getStatus().equals(filter)) {
                    Log.e("Equals",object.getStatus());
                    filterList.add(object);
                    Log.e("Clear2",filterList+"");

                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return this.filterList.size();

    }

    public void setRequestList(List<GetTasksResponse.Item> requestList,List<GetTasksResponse.Item> filterList){
        this.filterList = filterList;
        this.requestList = requestList;

        Log.e("List",requestList+"");
        Log.e("List",filterList+"");

        Log.e("Fuck5",requestList+"");
        for (int i = 0;i<requestList.size();i++){
            if(requestList.get(i).getStatus().equals("open")) {
                requestList.get(i).setStatus("Открытые");
            }else if (requestList.get(i).getStatus().equals("closed")){
                requestList.get(i).setStatus("Закрытые");
            }else if (requestList.get(i).getStatus().equals("in_progress")){
                requestList.get(i).setStatus("В процессе");
            }

        }
        Log.e("Fuck6",requestList+"");
        setData();
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
