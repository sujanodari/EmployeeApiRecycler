package com.sujan.employeeapirecycler.api;
import com.sujan.employeeapirecycler.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {
    //Retriving all employee
    @GET("employees")
    Call<List<Employee>>getAllEmployees();
}
