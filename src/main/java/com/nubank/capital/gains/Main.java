package com.nubank.capital.gains;

import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.nubank.capital.gains.dtos.Operation;

public class Main {

    private final static Gson GSON = new Gson();

    public static void main(String[] args) {
        try (final var scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                final var line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                final var operations = List.of(GSON.fromJson(line, Operation[].class));
                final var operationTax = OperationsTaxCalculator.calculateTax(operations);
                System.out.println(GSON.toJson(operationTax));
            }
        }
    }

}
