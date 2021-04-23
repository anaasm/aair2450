package aair2450mv.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    static Payment payment;

    @BeforeAll
    public static void createPayment(){
        payment=new Payment(2,PaymentType.Cash, 20.0);
    }

    @Test
    void getTableNumber() {
        assertEquals(payment.getTableNumber(), 2);
    }

    @Test
    void setTableNumber() {
        payment.setTableNumber(3);
        assertEquals(payment.getTableNumber(), 3);
        payment.setTableNumber(2);
    }

    @Test
    void getType() {
        assertEquals(payment.getType(), PaymentType.Cash);

    }

    @Test
    void setType() {
        payment.setType(PaymentType.Card);
        assertEquals(payment.getType(), PaymentType.Card);
        payment.setType(PaymentType.Cash);
    }

    @Test
    void getAmount() {
        assertEquals(payment.getAmount(), 20.0);
    }

    @Test
    void setAmount() {
        payment.setAmount(30.0);
        assertEquals(payment.getAmount(), 30.0);
        payment.setAmount(20.0);

    }
}