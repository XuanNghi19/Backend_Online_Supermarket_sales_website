package com.backend.Backend_supermarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptDTO {

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("partner_id")
    private Long partnerId;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    /*
     * Đã tạo
     * Đang nhập hàng
     * Đã nhập kho
     * Đã hoàn tất
     * Hủy bỏ
     * */
    @NotNull
    @JsonProperty("status")
    private String status;

    @NotNull
    @JsonProperty("delivery_date")
    private LocalDateTime deliveryDate;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("amount_paid")
    private Float amountPaid;

    @JsonProperty("note")
    private String note;

    @JsonProperty("ReceiptDetailDTO")
    private List<ReceiptDetailDTO> receiptDetailDTOS;

}
