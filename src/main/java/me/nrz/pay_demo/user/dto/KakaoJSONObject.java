package me.nrz.pay_demo.user.dto;

import me.nrz.pay_demo.user.dto.request.KaKaoPaymentRequest;
import org.json.simple.JSONObject;

public class KakaoJSONObject extends JSONObject {
    public KakaoJSONObject(KaKaoPaymentRequest kaKaoPaymentRequest){
        this.put("cid", kaKaoPaymentRequest.getCid());
        this.put("tid", kaKaoPaymentRequest.getTid());
        this.put("partner_order_id", kaKaoPaymentRequest.getPartner_order_id());
        this.put("partner_user_id", kaKaoPaymentRequest.getPartner_user_id());
        this.put("pg_token", kaKaoPaymentRequest.getPg_token());
    }
}
