package aair2450mv.service;

import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import aair2450mv.repository.MenuRepository;
import aair2450mv.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class Repo_Service_Entity_test {
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
        service.addPayment(3, PaymentType.Card, 20.0);
        List<Payment> list =  service.getPayments();
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getAmount(), 20.0);
    }

    @Test
    void getTotalAmount() {
        service.addPayment(3, PaymentType.Card, 20.0);
        service.addPayment(3, PaymentType.Cash, 30.0);
        assertEquals(service.getTotalAmount(PaymentType.Cash),30.0);
        assertEquals(service.getTotalAmount(PaymentType.Card),20.0);
    }

}