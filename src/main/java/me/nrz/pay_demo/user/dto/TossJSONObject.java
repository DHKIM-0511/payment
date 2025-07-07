package me.nrz.pay_demo.user.dto;

import me.nrz.pay_demo.user.dto.request.TossPaymentRequest;
import org.json.simple.JSONObject;

public class TossJSONObject extends JSONObject {
    public TossJSONObject(TossPaymentRequest tossPaymentRequest){
        this.put("orderId", tossPaymentRequest.getOrderId());
        this.put("amount", tossPaymentRequest.getAmount());
        this.put("paymentKey", tossPaymentRequest.getPaymentKey());
    }
}
