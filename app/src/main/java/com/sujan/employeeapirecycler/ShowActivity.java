package com.sujan.employeeapirecycler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sujan.employeeapirecycler.adapter.AdapterRecycleView;
import com.sujan.employeeapirecycler.api.EmployeeApi;
import com.sujan.employeeapirecycler.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShowActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private static String base_url = "http://dummy.restapiexample.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        recyclerView = findViewById(R.id.ReV);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
        final Call<List<Employee>> listCall = employeeApi.getAllEmployees();
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ShowActivity.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("error","error" +response.code() );
                    return;
                }
                List<Employee>listEmployee=response.body();
                AdapterRecycleView adapterRecycleView = new AdapterRecycleView(ShowActivity.this,listEmployee);
                recyclerView.setAdapter(adapterRecycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(ShowActivity.this));

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ShowActivity.this, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error","error   "+t.getLocalizedMessage() );

            }
        });
    }


}
