package com.gabe.hosanna.utils;

import com.gabe.hosanna.model.FilterData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface endpoints {



    @GET(".")
    Call<List<FilterData>> getFilterData();
}
