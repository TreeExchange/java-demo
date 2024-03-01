package com.tree.api.client.spot;

import com.tree.api.dto.CommonResponse;
import com.tree.api.dto.spot.SpotPostOrderRequest;


/**
 * @author zhouzhuang
 * @create 2023/9/20 11:48
 */
public interface SpotApiClient {


    CommonResponse postOrder(SpotPostOrderRequest request);

    CommonResponse getOrder(Long id);

    CommonResponse queryOrder(Long orderId);

    CommonResponse delOrder(Long id);
}
