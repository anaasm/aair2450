package aair2450mv.repository;

import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import aair2450mv.service.PizzaService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class Repo_Service_test {
    public static PaymentRepository paymentRepository;
    public static MenuRepository menuRepository;
    public static PizzaService service;

    @BeforeAll
    public static void setUp(){
        paymentRepository = new PaymentRepository("data/testAmount.txt");
        menuRepository = mock(MenuRepository.class);
        service = new PizzaService(menuRepository, paymentRepository);
    }

    @BeforeEach
    public void deleteAll(){
        paymentRepository.deleteAllPayments();
    }

    @Test
    void addPayment() {
        Payment payment = mock(Payment.class);
        Mockito.when(payment.getAmount()).thenReturn(20.0);
        Mockito.when(payment.getTableNumber()).thenReturn(3);
        Mockito.when(payment.getType()).thenReturn(PaymentType.Card);
        Mockito.when(payment.toString()).thenReturn(3 + ","+PaymentType.Card +"," + 20.0);
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

        service.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        service.addPayment(payment2.getTableNumber(), payment2.getType(), payment2.getAmount());
        assertEquals(service.getTotalAmount(PaymentType.Cash),30.0);
        assertEquals(service.getTotalAmount(PaymentType.Card),20.0);
    }

}