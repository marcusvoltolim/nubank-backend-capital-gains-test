package com.nubank.capital.gains.dtos;

import com.google.gson.annotations.SerializedName;

public enum OperationType {
    @SerializedName("buy")
    BUY,
    @SerializedName("sell")
    SELL
}
