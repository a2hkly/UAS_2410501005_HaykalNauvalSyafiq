package com.haykal.uascoba.api;

import com.haykal.uascoba.model.EndemicEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("data_endemik/endemik.json")
    Call<List<EndemicEntity>> getEndemicData();
}
