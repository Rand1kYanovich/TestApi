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
    private String[] choose;
    private String[] chooseServer;




    public DataAdapter( Context context, OnItemClickListener onItemClickListener){
        this.context = context;
        this.clickListener = onItemClickListener;
        this.filterList = new ArrayList<>();
        this.requestList = new ArrayList<>();
        this.choose = ((MainActivity)context).getResources().getStringArray(R.array.filterlist);
        this.chooseServer = ((MainActivity)context).getResources().getStringArray(R.array.filterlist_server);




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

        if(filter.equals(choose[0])){
            filterList = new ArrayList<GetTasksResponse.Item>(requestList);

        }
        else {
            for (int i = 0; i < requestList.size(); i++) {
                GetTasksResponse.Item object = requestList.get(i);
                if (object.getStatus().equals(filter)) {
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

    public void setRequestList(List<GetTasksResponse.Item> requestList,List<GetTasksResponse.Item> filterList){
        this.filterList = filterList;
        this.requestList = requestList;

        for (int i = 0;i<requestList.size();i++){
            for (int j=0;j<choose.length;j++){
                if(requestList.get(i).getStatus().equals(chooseServer[j])) requestList.get(i).setStatus(choose[j]);
            }
        }
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
