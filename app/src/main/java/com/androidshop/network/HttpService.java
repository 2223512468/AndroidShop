package com.androidshop.network;


import com.androidshop.constant.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit2.0 与OkHttp基本设置
 */
public class HttpService {

    /**
     * HttpService
     */
    private static HttpService mHttpService;
    private Api mApi;

    public static HttpService instance() {
        if (null == mHttpService) {
            mHttpService = new HttpService();
        }
        return mHttpService;
    }

    private HttpService() {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        {
                            Request localRequest = chain.request();
                            return chain.proceed(localRequest.newBuilder().header("api-key", "C69BAF41DA5ABD1FFEDC6D2FEA56B").header("accept", "application/vnd.picacomic.com.v1+json").header("app-version", "2.0.3.18").header("app-uuid", "37d0f888-b99f-3c47-b3aa-0513c0c7338a").header("app-platform", "android").header("app-build-version", "57").method(localRequest.method(), localRequest.body()).build());
                        }
                    }
                })
                .connectTimeout(50, TimeUnit.SECONDS)    //连接最大时长
                .readTimeout(30, TimeUnit.SECONDS)       //读取最大时长
                .writeTimeout(80, TimeUnit.SECONDS)      //写入最大时长
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.mApi = retrofit.create(Api.class);
    }

    public Api getApi() {
        return mApi;
    }
}
