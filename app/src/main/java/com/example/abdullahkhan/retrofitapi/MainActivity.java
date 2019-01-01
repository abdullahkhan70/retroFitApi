package com.example.abdullahkhan.retrofitapi;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JavaPlaceholderApi javaPlaceholderApi = retrofit.create(JavaPlaceholderApi.class);

        Call<List<Post>> call = javaPlaceholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());

                }

                List<Post> posts = response.body();


                for (Post post : posts){
                    String Content = "";
                    Content += "ID: " + post.getId() + "\n";
                    Content += "UserID: " + post.getUserId() + "\n";
                    Content += "Title: " + post.getTitle() + "\n";
                    Content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(Content);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }
}
