package aair2450mv.service;

import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import aair2450mv.repository.MenuRepository;
import aair2450mv.repository.PaymentRepository;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
        service.addPayment(1,PaymentType.Cash,10);
        double rez=service.getTotalAmount(PaymentType.Cash);
        assertEquals(10,rez);
    }

    @Order(3)
    @Test
    void getTotalAmountN2() {
        service.addPayment(2,PaymentType.Cash,5);
        double rez=service.getTotalAmount(PaymentType.Card);
        assertEquals(0,rez);
    }

    @Order(4)
    @Test
    void getTotalAmountNN() {
        service.addPayment(3,PaymentType.Cash,5);
        service.addPayment(3,PaymentType.Cash,20);
        double rez=service.getTotalAmount(PaymentType.Card);
        assertEquals(0,rez);
    }
}