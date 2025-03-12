package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class TransferRequest {

    @NotBlank(message = "From account number is required")
    @Pattern(regexp = "ACC\\d{3,6}", message = "From account number must follow format ACCXXX (e.g., ACC001)")
    private String fromAccountNum;

    @NotBlank(message = "To account number is required")
    @Pattern(regexp = "ACC\\d{3,6}", message = "To account number must follow format ACCXXX (e.g., ACC002)")
    private String toAccountNum;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than zero")
    private Double amount;

    // Getters and Setters
    public String getFromAccountNum() {
        return fromAccountNum;
    }

    public void setFromAccountNum(String fromAccountNum) {
        this.fromAccountNum = fromAccountNum;
    }

    public String getToAccountNum() {
        return toAccountNum;
    }

    public void setToAccountNum(String toAccountNum) {
        this.toAccountNum = toAccountNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
