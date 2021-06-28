package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionRepositoryTest {
    @Test
    void should_addDeposit() {

        int amount = 100;
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        when(clock.todayAsString()).thenReturn("2021/6/28");

        transactionRepository.addDeposit(amount);

        assertEquals(transactionRepository.transactions.get(0).amount(), amount);
    }

    @Test
    void should_addWithdraw() {
        int amount = 100;
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        when(clock.todayAsString()).thenReturn("2021/6/28");

        transactionRepository.addWithdraw(amount);

        assertAll(
                () -> assertEquals(1, transactionRepository.transactions.size()),
                () -> assertEquals(transactionRepository.transactions.get(0).amount(), -amount)
        );
    }

}