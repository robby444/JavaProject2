package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void visualizeExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> report = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String category = transaction.getDescription();
                report.putIfAbsent(category, 0.0);
                report.put(category, report.get(category) + Math.abs(transaction.getAmount()));
            }
        }

        System.out.println("Expenses Report by Category:");
        for (String category : report.keySet()) {
            double total = report.get(category);
            int stars = (int) (total / 1000); // Кожна зірочка представляє 1000 гривень
            StringBuilder visual = new StringBuilder();
            for (int i = 0; i < stars; i++) {
                visual.append('*');
            }
            System.out.printf("%-25s: %s\n", category, visual.toString());
//            System.out.printf("%s: %.2f %s\n", category, total, visual.toString());
        }
    }



}

