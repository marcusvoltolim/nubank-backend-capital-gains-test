package com.nubank.capital.gains;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.nubank.capital.gains.dtos.Operation;
import com.nubank.capital.gains.dtos.OperationCalculation;
import com.nubank.capital.gains.dtos.OperationTax;

public class OperationsTaxCalculator {

    private static final int MIN_VALUE = 20000;
    private static final double TAX_PERCENT = 0.2;

    public static List<OperationTax> calculateTax(final List<Operation> operations) {
        final var calculation = new OperationCalculation();

        return operations.stream()
            .map(op ->
                switch (op.operation()) {
                    case BUY -> buy(op, calculation);
                    case SELL -> sell(op, calculation);
                })
            .toList();

    }

    private static OperationTax buy(final Operation operationBuy, final OperationCalculation calculation) {
        final var newTotalShare = calculation.getTotalShares() + operationBuy.quantity();
        calculation.setAvgPrice((calculation.getTotalShares() * calculation.getAvgPrice() + operationBuy.quantity() * operationBuy.unitCost()) / newTotalShare);
        calculation.setTotalShares(newTotalShare);

        return new OperationTax(new BigDecimal("0.00"));
    }

    private static OperationTax sell(final Operation operationSell, final OperationCalculation calculation) {
        calculation.setTotalShares(calculation.getTotalShares() - operationSell.quantity());
        final var profitOrLoss = (operationSell.unitCost() - calculation.getAvgPrice()) * operationSell.quantity();
        double taxableProfit = 0;

        if (profitOrLoss > 0) {
            if (operationSell.calculateTotal() > MIN_VALUE) {
                taxableProfit = Math.max(profitOrLoss - calculation.getAccumulatedLoss(), 0);
            }
            calculation.setAccumulatedLoss(Math.max(calculation.getAccumulatedLoss() - profitOrLoss, 0));
        } else {
            calculation.setAccumulatedLoss(calculation.getAccumulatedLoss() + Math.abs(profitOrLoss));
        }

        return new OperationTax(new BigDecimal(taxableProfit * TAX_PERCENT).setScale(2, RoundingMode.HALF_UP));
    }

}
