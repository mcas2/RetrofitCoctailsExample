package com.mcas2.retrofitexample;

import android.hardware.lights.LightsManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView mainTV = findViewById(R.id.mainTV);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Drinks> call = apiInterface.getGinDrinks();
        call.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                Log.d("TAG", response.code()+"");
                Drinks drinks = response.body();
                String todo = "";
                for (Drinks.Coctail coctail : drinks.drinks) {
                    todo = todo + coctail.name;
                }
                Log.d("TODO", todo);
                mainTV.setText(todo);
           }

            @Override
            public void onFailure(Call<Drinks> call, Throwable throwable) {

            }
        });
    }
}