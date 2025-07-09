package me.nrz.pay_demo.user.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KaKaoPaymentRequest {

    @Min(value = 1, message = "userId must be greater than or equal to 1.")
    private int userId;

    @NotBlank(message = "cid is required.")
    private String cid;

    @NotBlank(message = "tid is required.")
    private String tid;

    @NotBlank(message = "partner_order_id is required.")
    private String partner_order_id;

    @NotBlank(message = "partner_user_id is required.")
    private String partner_user_id;

    @NotBlank(message = "pg_token is required.")
    private String pg_token;
}
