package com.tree.api.client.future;

import com.google.gson.Gson;
import com.tree.api.dto.FutureCommonResponse;
import com.tree.api.dto.future.FutureOrderCancelAllRequest;
import com.tree.api.dto.future.FutureOrderCancelRequest;
import com.tree.api.dto.future.FuturePostOrderRequest;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

/**
 * @author zhouzhuang
 * @create 2023/9/20 18:23
 */
public abstract class AbstractFutureApiClient implements FutureApiClient {
    private final Gson gson = new Gson();
    @Override
    public FutureCommonResponse postOrder(FuturePostOrderRequest request) {
        return executeSync(getService().postOrder(request));
    }
    @Override
    public FutureCommonResponse batchOrder(List<FuturePostOrderRequest> futurePostOrderRequestList){
        return executeSync(getService().batchOrder(gson.toJson(futurePostOrderRequestList)));
    }

    @Override
    public FutureCommonResponse orderListHistory(Map<String, String> params) {
        return executeSync(getService().orderListHistory(params));
    }

    @Override
    public FutureCommonResponse orderTradeList(Map<String, String> params) {
        return executeSync(getService().orderTradeList(params));
    }

    @Override
    public FutureCommonResponse orderDetail(Long orderId) {
        return executeSync(getService().orderDetail(orderId));
    }
    @Override
    public FutureCommonResponse orderList(Map<String, String> params){
        return executeSync(getService().orderList(params));
    }
    @Override
    public FutureCommonResponse orderCancel(Long orderId){
        return executeSync(getService().orderCancel(FutureOrderCancelRequest.builder().orderId(orderId).build()));
    }

    @Override
    public FutureCommonResponse allCancel(String symbol){
        symbol = symbol==null?"":symbol;
        return executeSync(getService().allCancel(FutureOrderCancelAllRequest.builder().symbol(symbol).build()));
    }

    @Override
    public FutureCommonResponse entrustCreatePlan(Map<String, String> params){
        return executeSync(getService().entrustCreatePlan(params));
    }

    @Override
    public FutureCommonResponse entrustCancelPlan(Long entrustId){
        return executeSync(getService().entrustCancelPlan(entrustId));
    }

    @Override
    public FutureCommonResponse entrustCancelAllPlan(String symbol){
        return executeSync(getService().entrustCancelAllPlan(symbol));
    }

    @Override
    public FutureCommonResponse accountInfo(){
        return executeSync(getService().accountInfo());
    }

    @Override
    public FutureCommonResponse balanceDetail(String coin){
        return executeSync(getService().balanceDetail(coin));
    }

    @Override
    public FutureCommonResponse listenKey(){
        return executeSync(getService().listenKey());
    }

    @Override
    public FutureCommonResponse adjustLeverage(String symbol,String positionSide,Integer leverage){
        return executeSync(getService().adjustLeverage(symbol,positionSide,leverage));
    }

    public FutureCommonResponse executeSync(Call<FutureCommonResponse> call) {
        try {
            Response<FutureCommonResponse> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }else {
                System.err.println(String.format("failed to call interface:%s,%s",response.code(),response.toString()));
                return FutureCommonResponse.failure(response.toString());
            }
        }catch (Exception e){
            System.err.println("call interface exception:"+e);
            throw new RuntimeException(e);
        }
    }

    abstract FutureApiService getService();
}
