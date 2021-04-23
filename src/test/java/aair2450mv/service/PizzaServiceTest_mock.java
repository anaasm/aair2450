package aair2450mv.service;

import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import aair2450mv.repository.MenuRepository;
import aair2450mv.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class PizzaServiceTest_mock {
    public static PizzaService service;
    public static MenuRepository menuRepository;
    public static PaymentRepository paymentRepository;

    @BeforeAll
    public static void setUp(){
        paymentRepository=mock(PaymentRepository.class);
        menuRepository=mock(MenuRepository.class);
        service=new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    void getPayments() {
    }

    @Test
    void addPayment() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getAmount()).thenReturn(20.0);
        Mockito.when(payment.getTableNumber()).thenReturn(3);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Card);
        Mockito.when(payment.toString()).thenReturn(3 + ","+PaymentType.Card +"," + 20.0);
        Mockito.when(paymentRepository.getAll()).thenReturn(Arrays.asList(payment));

        service.addPayment(3, PaymentType.Card, 20.0);
        List<Payment> list =  service.getPayments();
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getAmount(), 20.0);
    }

    @Test
    void getTotalAmount() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getAmount()).thenReturn(20.0);
        Mockito.when(payment.getTableNumber()).thenReturn(3);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Card);
        Mockito.when(payment.toString()).thenReturn(3 + ","+PaymentType.Card +"," + 20.0);

        Payment payment2 = mock(Payment.class);
        Mockito.when(payment2.getAmount()).thenReturn(30.0);
        Mockito.when(payment2.getTableNumber()).thenReturn(3);
        Mockito.when(payment2.getType()).thenReturn(PaymentType.Cash);
        Mockito.when(payment2.toString()).thenReturn(3 + ","+PaymentType.Cash +"," + 30.0);

        Mockito.when(paymentRepository.getAll()).thenReturn(Arrays.asList(payment, payment2));
        assertEquals(service.getTotalAmount(PaymentType.Cash),30.0);
        assertEquals(service.getTotalAmount(PaymentType.Card),20.0);
    }
}