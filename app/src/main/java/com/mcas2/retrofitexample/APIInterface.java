package com.mcas2.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("filter.php?i=Gin")
    Call<Drinks> getGinDrinks();

}
