package com.nubank.capital.gains.dtos;

import com.google.gson.annotations.SerializedName;

public record Operation(
    OperationType operation,
    @SerializedName("unit-cost")
    double unitCost,
    int quantity
) {

    public double calculateTotal() {
        return unitCost * quantity;
    }

}
