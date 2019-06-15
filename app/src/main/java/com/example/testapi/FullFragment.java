package com.example.testapi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.api.Get;
import com.example.testapi.api.NetworkService;
import com.example.testapi.api.SecondGet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FullFragment extends Fragment {
    private TextView title,actual_time,location,status,description,specialist;
    private Button button_get_down;
    private String id;
    private Context context;


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

        title = rootView.findViewById(R.id.title);
        actual_time = rootView.findViewById(R.id.actual_time);
        location = rootView.findViewById(R.id.location);
        status = rootView.findViewById(R.id.status);
        description = rootView.findViewById(R.id.description);
        specialist = rootView.findViewById(R.id.specialist);

        NetworkService.getInstance()
                .getJSONApi()
                .getFullList(id)
                .enqueue(new Callback<SecondGet>() {
                    @Override
                    public void onResponse(Call<SecondGet> call, Response<SecondGet> response) {
                        SecondGet.SecondItem listInfo = response.body().getData();
                        if(!response.body().getStatus()){
                            Log.e("List",response.body().getError());
                            Toast.makeText(context,response.body().getError(),Toast.LENGTH_SHORT).show();

                        }
                        else {

                            title.setText(listInfo.getTitle());
                            actual_time.setText(listInfo.getActualTime() + "");
                            location.setText(listInfo.getLocation());
                            status.setText(listInfo.getStatus());
                            description.setText(listInfo.getDescription());
                            SecondGet.SecondItem.Specialist specialistInfo = listInfo.getSpecialist();

                            if (specialistInfo == null) {
                                Toast.makeText(context, "Фамилия и Имя не указаны", Toast.LENGTH_SHORT).show();
                            } else {
                                specialist.setText(specialistInfo.getFirst_name() + " " + specialistInfo.getLast_name());
                            }

                            if(listInfo.getStatus().equals("open")){
                                button_get_down = rootView.findViewById(R.id.button_get_down);
                                button_get_down.setVisibility(View.VISIBLE);
                                button_get_down.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(context,"Извините, данный функционал еще в разработке",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<SecondGet> call, Throwable t) {

                    }
                });


        return rootView;
    }


}
