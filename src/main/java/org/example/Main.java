package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("\n");

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        System.out.println("\n");

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        System.out.println("\n");

        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseOverPeriod(transactions, "13-01-2024", "25-01-2024");
        Transaction minExpense = TransactionAnalyzer.findMinExpenseOverPeriod(transactions, "13-01-2024", "25-01-2024");

        System.out.println("Period: \"13-01-2024\" - \"25-01-2024\"");
        System.out.println("Max expense: \n" + maxExpense);
        System.out.println("Min expense: \n" + minExpense);

        System.out.println("\n");

        TransactionReportGenerator.visualizeExpensesByCategory(transactions);
    }
}
