package aair2450mv.repository;

import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PaymentRepositoryTest {
    private static PaymentRepository paymentRepository;

    @BeforeAll
    public static void setUp(){
       paymentRepository = new PaymentRepository("data/testAmount.txt");
    }

    @AfterAll
    public static void clearFile() throws IOException {
        paymentRepository.deleteAllPayments();
    }

    @Test
    void deleteAllPayments() {
        Payment payment = mock(Payment.class);
        paymentRepository.add(payment);
        int currentSize=paymentRepository.getAll().size();
        paymentRepository.deleteAllPayments();
        assertNotEquals(currentSize,0);
        assertEquals(paymentRepository.getAll().size(),0);
    }


    @Test
    void add() {
        Payment payment = mock(Payment.class);
        int currentSize=paymentRepository.getAll().size();
        Mockito.when(payment.getAmount()).thenReturn(20.0);
        Mockito.when(payment.getTableNumber()).thenReturn(3);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Card);
        Mockito.when(payment.toString()).thenReturn(3 + ","+PaymentType.Card +"," + 20.0);
        paymentRepository.add(payment);
        System.out.println(paymentRepository.getAll().size());
        assertEquals(paymentRepository.getAll().get(0).getAmount(),20.0);
        assertEquals(paymentRepository.getAll().size(), currentSize+1);
    }
}