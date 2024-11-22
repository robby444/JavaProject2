package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer {
//    private List<Transaction> transactions;

//    public TransactionAnalyzer(List<Transaction> transactions) {
//        this.transactions = transactions;
//    }

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    // Тут будуть інші методи для аналізу транзакцій
    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    public static Transaction findMaxExpenseOverPeriod(List<Transaction> transactions, String startDate, String endDate) {
        double maxExpense = transactions.getFirst().getAmount();
        Transaction result = null;
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            if ((transactionDate.isEqual(start) || transactionDate.isAfter(start)) &&
                    (transactionDate.isEqual(end) || transactionDate.isBefore(end))) {
                if (transaction.getAmount() < maxExpense && transaction.getAmount() < 0) {
                    maxExpense = transaction.getAmount();
                    result = transaction;
                }
            }
        }

        return result;

    }

    public static Transaction findMinExpenseOverPeriod(List<Transaction> transactions, String startDate, String endDate) {
        double minAmount = transactions.getFirst().getAmount();
        Transaction result = null;
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), DATE_FORMATTER);
            if ((transactionDate.isEqual(start) || transactionDate.isAfter(start)) &&
                    (transactionDate.isEqual(end) || transactionDate.isBefore(end))) {
                if (transaction.getAmount() > minAmount && transaction.getAmount() < 0) {
                    minAmount = transaction.getAmount();
                    result = transaction;
                }
            }
        }

        return result;

    }


}

