package com.tree.api.client.future;

import com.tree.api.client.OkHttpClientBuilder;
import com.tree.api.interceptor.FutureOkHttpInterceptor;
import com.tree.api.client.HttpProxyProperties;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * U-BASE future
 * @author zhouzhuang
 * @create 2023/9/20 12:18
 */
public class UFutureApiClientImpl extends AbstractFutureApiClient {

    private final static String API_URL = "http://fapi.treeexchange.io";

    private final FutureApiService service;

    public UFutureApiClientImpl(HttpProxyProperties proxyProperties){
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .client(OkHttpClientBuilder.build(proxyProperties,new FutureOkHttpInterceptor()))
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
        service = retrofit.create(FutureApiService.class);
    }

    @Override
    FutureApiService getService() {
        return service;
    }


}
