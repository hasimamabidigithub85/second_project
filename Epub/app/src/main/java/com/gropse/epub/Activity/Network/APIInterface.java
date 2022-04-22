package com.gropse.epub.Activity.Network;


import com.gropse.epub.Activity.Model.BookModel;
import com.gropse.epub.Activity.Model.GetTitleModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("GetEbook")
    Call<ArrayList<BookModel>> createView(
            @Field("Class") String Class,
            @Field("Medium") String medium);

    @GET("GetTeacherTitle")
    Call<ArrayList<GetTitleModel>> getTeacherTraining();


}