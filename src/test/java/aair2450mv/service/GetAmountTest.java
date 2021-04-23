package aair2450mv.service;

import aair2450mv.model.PaymentType;
import aair2450mv.repository.MenuRepository;
import aair2450mv.repository.PaymentRepository;
import org.junit.jupiter.api.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GetAmountTest {
    private static MenuRepository menuRepository;
    private static PaymentRepository paymentRepository;
    private static PizzaService service;
    private static PaymentType type;


    @BeforeAll
    public static void classSetup(){
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository("data/testAmount.txt");
        service = new PizzaService(menuRepository, paymentRepository);
        type = PaymentType.Cash;
        paymentRepository.deleteAllPayments();
    }

    @AfterAll
    public static void clearFile() throws IOException {
       paymentRepository.deleteAllPayments();
    }

    @Order(1)
    @Test
    void getTotalAmountN0() {
        double rez=service.getTotalAmount(PaymentType.Cash);
        assertEquals(0,rez);
    }

    @Order(2)
    @Test
    void getTotalAmountN1() {
        service.addPayment(1,PaymentType.Cash,10.0);
        double rez=service.getTotalAmount(PaymentType.Cash);
        assertEquals(10,rez);
    }

    @Order(3)
    @Test
    void getTotalAmountN2() {
        service.addPayment(2,PaymentType.Cash,5.0);
        double rez=service.getTotalAmount(PaymentType.Card);
        assertEquals(0,rez);
    }

    @Order(4)
    @Test
    void getTotalAmountNN() {
//        Payment p = new Payment(3,PaymentType.Cash,5);
//        Payment p2 = new Payment(3,PaymentType.Cash,20);
//
//        service.addPayment(p);
//        service.addPayment(p2);
        service.addPayment(3,PaymentType.Cash,5.0);
        service.addPayment(3,PaymentType.Cash,20.0);
        double rez=service.getTotalAmount(PaymentType.Card);
        assertEquals(0,rez);
    }

    @Order(5)
    @Test
    void getTotalAmountNotVal() {
//        Payment p = new Payment(3,PaymentType.Cash,5);
//        Payment p2 = new Payment(3,PaymentType.Cash,20);
//
//        service.addPayment(p);
//        service.addPayment(p2);
        service.addPayment(3,PaymentType.Cash,5.0);
        service.addPayment(3,PaymentType.Cash,20.0);

        assertEquals(service.getTotalAmount(null),0);
    }
}