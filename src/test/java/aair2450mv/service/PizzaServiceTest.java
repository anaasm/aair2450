package aair2450mv.service;

import aair2450mv.model.Payment;
import aair2450mv.model.PaymentType;
import aair2450mv.repository.MenuRepository;
import aair2450mv.repository.PaymentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PizzaServiceTest {
    private static MenuRepository menuRepository;
    private static PaymentRepository paymentRepository;
    private static PizzaService service;
    private static PaymentType type;


    @BeforeAll
    public static void classSetup(){
        menuRepository = new MenuRepository();
        paymentRepository = new PaymentRepository();
        service = new PizzaService(menuRepository, paymentRepository);
        type = PaymentType.Cash;
    }

    @DisplayName("valid data")
    @Tag("ECP")
    @Order(1)
    @Test
    void valid_ECP() {
        //arrange
        int nrMasa=5;
        double suma = 10.5;

        Payment lastPayment;
        int nrPayment = service.getPayments().size();

        //act
        service.addPayment(nrMasa, type, suma);

        //assert
        assertEquals(nrPayment+1,service.getPayments().size());
        try{
            lastPayment = service.getPayments().get(service.getPayments().size()-1);
            assertEquals(lastPayment.getTableNumber(),nrMasa);
            assertEquals(lastPayment.getAmount(), suma);
            assertEquals(lastPayment.getType(), type);
        }catch (Exception ex){
            assertTrue(false);
        }
    }

    @Tag("BVA")
    @DisplayName("valid data")
    @Order(3)
    @ParameterizedTest
    @ValueSource(ints = {1,2,7,8})
    void valid_BVA(int nrMasa) {
        //arrange
        double suma = 1;
        PaymentType type= PaymentType.Cash;
        int nrPayment = service.getPayments().size();
        Payment lastPayment;

        //act
        service.addPayment(nrMasa, type, suma);

        //assert
        assertEquals(nrPayment+1,service.getPayments().size());
        try{
            lastPayment = service.getPayments().get(service.getPayments().size()-1);
            assertEquals(lastPayment.getTableNumber(),nrMasa);
            assertEquals(lastPayment.getAmount(), suma);
            assertEquals(lastPayment.getType(), type);
        }catch (Exception ex){
            assertTrue(false);
        }
    }

    @Tag("BVA")
    @DisplayName("invalid tableNo and invalid amount")
    @Order(4)
    @ParameterizedTest
    @ValueSource(ints = {0,9})
    void nonvalid_BVA(int nrMasa) {
        //arrange
        double suma = -1;
        PaymentType type= PaymentType.Cash;
        int nrPayment = service.getPayments().size();

        //act
        service.addPayment(nrMasa, type, suma);

        //assert
        assertEquals(nrPayment,service.getPayments().size());
    }


    @ParameterizedTest
    @ValueSource(ints = {-5,200})
    @Tag("ECP")
    @DisplayName("invalid tableNo and invalid amount")
    @Order(2)
    void nonvalid_ECP(int nrMasa) {
        //arrange
        double suma = -50;
        int nrPayment = service.getPayments().size();

        //act
        service.addPayment(nrMasa, type, suma);

        //assert
        assertEquals(nrPayment,service.getPayments().size());
    }


    @Disabled
    @Test
    void disabledTest(){

    }
}