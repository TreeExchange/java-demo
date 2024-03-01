package com.tree.api.client.future;

import com.tree.api.client.OkHttpClientBuilder;
import com.tree.api.client.HttpProxyProperties;
import com.tree.api.interceptor.FutureOkHttpInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * B-BASE Future
 * @author zhouzhuang
 * @create 2023/9/20 12:18
 */
public class BFutureApiClientImpl extends AbstractFutureApiClient {

    private final static String API_URL = "http://dapi.treeexchange.io";

    private final FutureApiService service;

    public BFutureApiClientImpl(HttpProxyProperties proxyProperties){
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
