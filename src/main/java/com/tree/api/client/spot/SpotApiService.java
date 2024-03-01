package com.tree.api.client.spot;

import com.tree.api.dto.CommonResponse;
import com.tree.api.dto.spot.SpotPostOrderRequest;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author zhouzhuang
 * @create 2023/9/20 14:28
 */
public interface SpotApiService {

    @POST("/v4/order")
    Call<CommonResponse> postOrder(@Body SpotPostOrderRequest request);

    @GET("/v4/order/{id}")
    Call<CommonResponse> getOrder(@Path("id")Long id);

    @GET("/v4/order")
    Call<CommonResponse> queryOrder(@Query("orderId") Long orderId);

    @DELETE("/v4/order/{id}")
    Call<CommonResponse> delOrder(@Path("id")Long id);
}
