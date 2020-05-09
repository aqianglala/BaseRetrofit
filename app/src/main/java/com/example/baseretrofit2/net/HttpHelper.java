package com.example.baseretrofit2.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper {

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static OkHttpClient.Builder addInterceptor() {
            // 这里添加项目中需要的 interceptor
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            BUILDER.addInterceptor(logging);
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建Retrofit，记得设置 base_url
     */
    public static final class RetrofitHolder {
        private static final String BASE_URL = "https://www.wanandroid.com/";
        static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static final class RestServiceHolder {
        private static final ApiService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    public static ApiService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

}
