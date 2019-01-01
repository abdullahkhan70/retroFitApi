package com.example.abdullahkhan.retrofitapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonCommentsData {

    @GET("posts/1/comments")
    Call<List<Comments>> getComments(
            @Query("postId") Integer[] postId,
            @Query("_sort") String sorting,
            @Query("_order") String order
    );
    @POST("posts")
    Call<Comments> createPost(@Body Comments comments);
}
