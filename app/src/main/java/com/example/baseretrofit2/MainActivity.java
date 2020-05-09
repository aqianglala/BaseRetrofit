package com.example.baseretrofit2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baseretrofit2.bean.Banner;
import com.example.baseretrofit2.net.HttpHelper;
import com.example.baseretrofit2.net.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Call<Result<List<Banner>>> mBannerCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBannerCall = HttpHelper.getRestService().getBanner();
        mBannerCall.enqueue(new Callback<Result<List<Banner>>>() {
            @Override
            public void onResponse(Call<Result<List<Banner>>> call, Response<Result<List<Banner>>> response) {
                Toast.makeText(MainActivity.this, response.body().getData().size() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Result<List<Banner>>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBannerCall != null && !mBannerCall.isCanceled()) {
            mBannerCall.cancel();
        }
    }
}
