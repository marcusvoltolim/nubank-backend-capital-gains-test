package com.nubank.capital.gains;

import java.util.List;
import com.nubank.capital.gains.dtos.Operation;
import com.nubank.capital.gains.dtos.OperationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationsTaxCalculatorTest {

    @Test
    void calculateTaxCase1() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 100),
            new Operation(OperationType.SELL, 15.00, 50),
            new Operation(OperationType.SELL, 15.00, 50)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00]]", result.toString());
    }

    @Test
    void calculateTaxCase2() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.SELL, 20.00, 5000),
            new Operation(OperationType.SELL, 5.00, 5000)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=10000.00], OperationTax[tax=0.00]]", result.toString());
    }

    @Test
    void calculateTaxCase3() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.SELL, 5.00, 5000),
            new Operation(OperationType.SELL, 20.00, 3000)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=1000.00]]", result.toString());
    }

    @Test
    void calculateTaxCase4() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.BUY, 25.00, 5000),
            new Operation(OperationType.SELL, 15.00, 10000)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00]]", result.toString());
    }

    @Test
    void calculateTaxCase5() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.BUY, 25.00, 5000),
            new Operation(OperationType.SELL, 15.00, 10000),
            new Operation(OperationType.SELL, 25.00, 5000)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=10000.00]]", result.toString());
    }

    @Test
    void calculateTaxCase6() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.SELL, 2.00, 5000),
            new Operation(OperationType.SELL, 20.00, 2000),
            new Operation(OperationType.SELL, 20.00, 2000),
            new Operation(OperationType.SELL, 25.00, 1000)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=3000.00]]", result.toString());
    }

    @Test
    void calculateTaxCase7() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.SELL, 2.00, 5000),
            new Operation(OperationType.SELL, 20.00, 2000),
            new Operation(OperationType.SELL, 20.00, 2000),
            new Operation(OperationType.SELL, 25.00, 1000),
            new Operation(OperationType.BUY, 20.00, 1000),
            new Operation(OperationType.SELL, 15.00, 5000),
            new Operation(OperationType.SELL, 30.00, 4350),
            new Operation(OperationType.SELL, 30.00, 650)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=3000.00], " +
            "OperationTax[tax=0.00], OperationTax[tax=0.00], OperationTax[tax=3700.00], OperationTax[tax=0.00]]", result.toString());
    }

    @Test
    void calculateTaxCase8() {
        final var operations = List.of(
            new Operation(OperationType.BUY, 10.00, 10000),
            new Operation(OperationType.SELL, 50.00, 10000),
            new Operation(OperationType.BUY, 20.00, 10000),
            new Operation(OperationType.SELL, 50.00, 10000)
        );

        final var result = OperationsTaxCalculator.calculateTax(operations);

        assertEquals("[OperationTax[tax=0.00], OperationTax[tax=80000.00], OperationTax[tax=0.00], OperationTax[tax=60000.00]]", result.toString());
    }

}
