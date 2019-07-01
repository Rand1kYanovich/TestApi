package com.example.testapi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.api.NetworkService;
import com.example.testapi.api.SecondGetTasksResponse;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FullFragment extends Fragment {
    private TextView tvTitle,tvActualTime,tvLocation,tvStatus,tvDescription,tvSpecialist;
    private Button btnGetDown;
    private String id;
    private Context context;
    private String[] choose;
    private String[] chooseServer;



    public  FullFragment(String id,Context context) {
        this.id = id;
        this.context = context;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_full,container,false);


        choose = getResources().getStringArray(R.array.filterlist);
        chooseServer = getResources().getStringArray(R.array.filterlist_server);


        tvTitle = rootView.findViewById(R.id.tvTitle);
        tvActualTime = rootView.findViewById(R.id.tvActualTime);
        tvLocation = rootView.findViewById(R.id.tvLocation);
        tvStatus = rootView.findViewById(R.id.tvStatus);
        tvDescription = rootView.findViewById(R.id.tvDescription);
        tvSpecialist = rootView.findViewById(R.id.tvSpecialist);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {

        NetworkService.getInstance()
                .getJSONApi()
                .getFullList(id)
                .enqueue(new Callback<SecondGetTasksResponse>() {
                    @Override
                    public void onResponse(Call<SecondGetTasksResponse> call, Response<SecondGetTasksResponse> response) {
                        SecondGetTasksResponse.SecondItem listInfo = response.body().getData();
                        if(!response.body().getStatus()){
                            Toast.makeText(context,response.body().getError(),Toast.LENGTH_SHORT).show();

                        }
                        else {

                            tvTitle.setText(listInfo.getTitle());
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
                            long second = Long.valueOf(listInfo.getActualTime())*1000;
                            String time = simpleDateFormat.format(second);
                            tvActualTime.setText(time);
                            tvLocation.setText(listInfo.getLocation());
                            for(int i = 0;i<choose.length;i++){
                                if(listInfo.getStatus().equals(chooseServer[i])) listInfo.setStatus(choose[i]);
                            }
                            tvDescription.setText(listInfo.getDescription());
                            SecondGetTasksResponse.SecondItem.Specialist specialistInfo = listInfo.getSpecialist();

                            if (specialistInfo == null) {
                                Toast.makeText(context, getText(R.string.error_name), Toast.LENGTH_SHORT).show();
                            } else {
                                tvSpecialist.setText(specialistInfo.getFirst_name() + " " + specialistInfo.getLast_name());
                            }
                            if(listInfo.getStatus().equals(choose[1])){
                                btnGetDown = view.findViewById(R.id.btnGetDown);
                                btnGetDown.setVisibility(View.VISIBLE);
                                btnGetDown.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setTitle(getText(R.string.alert_title))
                                                .setMessage(getText(R.string.alert_message))
                                                .setNegativeButton(getText(R.string.alert_btn),
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                });


                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<SecondGetTasksResponse> call, Throwable t) {

                    }
                });

    }
}
