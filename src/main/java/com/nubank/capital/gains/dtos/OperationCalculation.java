package com.nubank.capital.gains.dtos;

import java.text.DecimalFormat;

public class OperationCalculation {

    private double totalShares;
    private double avgPrice;
    public double accumulatedLoss;

    public double getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(double totalShares) {
        this.totalShares = totalShares;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = Double.parseDouble(new DecimalFormat("0.00").format(avgPrice));
    }

    public double getAccumulatedLoss() {
        return accumulatedLoss;
    }

    public void setAccumulatedLoss(double accumulatedLoss) {
        this.accumulatedLoss = accumulatedLoss;
    }

}
