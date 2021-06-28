package com.tw.banking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountTest {

    @Test
    void should_deposit() {
        //given
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        int amount = 100;
        doNothing().when(transactionRepository).addDeposit(amount);

        //when
        account.deposit(100);

        //then
        verify(transactionRepository, times(1)).addDeposit(amount);
    }

    @Test
    void should_withdraw() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        Account account = new Account(transactionRepository, printer);
        int amount = 100;
        doNothing().when(transactionRepository).addWithdraw(amount);

        //when
        account.withdraw(100);

        //then
        verify(transactionRepository, times(1)).addWithdraw(amount);
    }

    @Test
    void should_printStatement() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        Clock clock = mock(Clock.class);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(clock.todayAsString(), 100));
        when(transactionRepository.allTransactions()).thenReturn(transactions);
        doNothing().when(printer).print(anyList());
        Account account = new Account(transactionRepository, printer);

        //when
        account.printStatement();

        //then
        verify(transactionRepository, times(1)).allTransactions();
        verify(printer, times(1)).print(anyList());
    }
}
