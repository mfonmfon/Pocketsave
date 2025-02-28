package com.pocketsave.africa.nija.pocketsave.Dtos;

import java.math.BigDecimal;

public class DepositRequest {
    private Long customerId;
    private BigDecimal amount;
    private String description;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
