package com.example.testapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataViewHolders>{
    private List<Request> requestList;
    private Context context;
    private OnItemClickListener clickListener;


    public DataAdapter(List<Request> matchesList, Context context,OnItemClickListener onItemClickListener){
        this.requestList = matchesList;
        this.context = context;
        this.clickListener = onItemClickListener;

        requestList.add(new Request("Hi girls",123,"Volgograd","All Right baby"));
        requestList.add(new Request("Hi girls",321,"Volgograd","All "));
        requestList.add(new Request("Hi girls",222,"gograd","All Right baby"));
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
        final Request request = requestList.get(position);
        holder.title.setText(request.getTitle());

        holder.date.setText(request.getDate()+"");
        holder.location.setText(request.getLocation());
        holder.status.setText(request.getStatus());
        holder.bind(position,clickListener);


    }


    @Override
    public int getItemCount() {
        return this.requestList.size();
    }


}

class DataViewHolders extends RecyclerView.ViewHolder  {
    public TextView title,date,location,status;





    public DataViewHolders(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        date = itemView.findViewById(R.id.date);
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
