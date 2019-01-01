package com.example.abdullahkhan.retrofitapi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comment extends AppCompatActivity {

    TextView commentResult;
    JsonCommentsData jsonCommentsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        commentResult = findViewById(R.id.comment_ID);

        Retrofit retrofitComments = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonCommentsData = retrofitComments.create(JsonCommentsData.class);

        getComments();

        postComments();

    }

    private void postComments() {
        Comments comments = new Comments(14,"Abdullah Khan","abdullahkhan9003@gmail.com","Hello World!!!!")

        Call<Comments> commentsCall = jsonCommentsData.createPost(comments);

        commentsCall.enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if(!response.isSuccessful()){
                   Toast.makeText(Comment.this,"" + response.code(),Toast.LENGTH_LONG).show();
                }

                Comments postComments = response.body();

                String getComments = "";
                getComments += "Post ID: " + postComments.getpostId() + "\n";
                getComments += "ID: " + postComments.getId() + "\n";
                getComments += "Name: " + postComments.getName() + "\n";
                getComments += "Email: " + postComments.getEmail() + "\n";
                getComments += "Body: " + postComments.getText() + "\n\n";

                commentResult.append(getComments);
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {

            }
        });
    }

    private void getComments(){
        Call<List<Comments>> callComments = jsonCommentsData.getComments(null,"id","desc");

        callComments.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comments>> call, @NonNull Response<List<Comments>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Comment.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                }

                List<Comments> CommmentPosts;
                CommmentPosts = response.body();

                assert CommmentPosts != null;
                for (Comments commentPosts : CommmentPosts){
                    String getComments = "";
                    getComments += "Post ID: " + commentPosts.getpostId() + "\n";
                    getComments += "ID: " + commentPosts.getId() + "\n";
                    getComments += "Name: " + commentPosts.getName() + "\n";
                    getComments += "Email: " + commentPosts.getEmail() + "\n";
                    getComments += "Body: " + commentPosts.getText() + "\n\n";

                    commentResult.append(getComments);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Comments>> call, @NonNull Throwable t) {
                Toast.makeText(Comment.this,"" + t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
