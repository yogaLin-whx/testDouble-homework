package com.tw.banking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class PrinterTest {

    @Test
    void should_print() {
        Console console = mock(Console.class);
        Printer printer = new Printer(console);

        printer.print(new ArrayList<Transaction>());

        verify(console, times(1)).printLine(anyString());
    }
}